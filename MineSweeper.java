/**
 * MineSweeper - Lab 7&8
 * 
 * @author Alex Louden
 * @website alexlouden.com
 * @date 20.09.09
 */

import java.awt.event.*; //mouselistener
import java.awt.Font; //fonts
import javax.swing.Timer;

public class MineSweeper implements MouseListener
{
    private SimpleCanvas sc;
    
    public static int SQUARE_SIZE = 20;
    
    private static double MINE_CHANCE = 0.1; //chance of getting a mine
    
    private int CANVAS_HEIGHT;
    private int CANVAS_WIDTH;
    
    public static int CANVAS_SIDES = 15;
    public static int CANVAS_TOP = 50;
    public static int CANVAS_BOTTOM = 30;
    
    /* Fireworks */
    
    private static final int NUM_PARTICLES = 1000;
    private static final int VELOCITY = 10; //velocity of particles
        
    private double[] firework_x; //position
    private double[] firework_y;
    
    private double[] firework_prev_x; //previous position
    private double[] firework_prev_y;
    
    private double[] firework_vel_x; //velocity
    private double[] firework_vel_y;
    
    private int firework_loc_x;
    private int firework_loc_y;
    
    private int rgb;
    
    private Timer fireworks_timer;
    private Timer timertimer;
    
    private int if_red; //0 or 1
    private int if_green;
    private int if_blue;
    
    private int[] minearray_x;
    private int[] minearray_y;
    private int q; //index in minearray 
    private int current_q; //when going through
    private int[] done_q;
    
    /* Colours */
    
    public static java.awt.Color SQUARE_COLOUR =       new java.awt.Color(192,192,192);  //default tile
    public static java.awt.Color DUG_COLOUR =          new java.awt.Color(210,210,210);  //dug tile
    public static java.awt.Color BAD_DIG_COLOUR =      java.awt.Color.red;               //background of tile of bad dig

    public static java.awt.Color GRID_DARK_COLOUR =    new java.awt.Color(128,128,128);
    public static java.awt.Color GRID_LIGHT_COLOUR =   java.awt.Color.white; 
    
    public static java.awt.Color BACKGROUND_COLOUR =   new java.awt.Color(255,255,255);  //window background
    public static java.awt.Color FOREGROUND_COLOUR =   java.awt.Color.black;             //text
    
    public static java.awt.Color MINE_COLOUR =         java.awt.Color.black;             //mine foreground
    public static java.awt.Color FLAG_TOP_COLOUR =     java.awt.Color.red;
    public static java.awt.Color FLAG_BASE_COLOUR =    java.awt.Color.black;

    /* Fonts */
    
    public static Font defaultFont =    new Font ("Arial",Font.BOLD,SQUARE_SIZE*3/5);
    public static Font mineCountFont =  new Font ("DS-Digital",Font.BOLD, CANVAS_TOP*4/5);
    public static Font messageFont =  new Font ("Arial",Font.BOLD,12);    
    
    /* Status */
    
    public enum Status {CLEAR, MINE, GOOD_MARK, BAD_MARK, DUG, BAD_DIG};
    public enum GStatus {FIRST, PLAYING, OVER};
    
    private GStatus GameStatus;
    private Status[][] Field;
    
    private int cols;
    private int rows;
    private int minestotal = 0;
    private int minesleft;
    
    //for searching for blank (no nearby) tiles
    private int[] blankX;
    private int[] blankY;
    private boolean blankSearch;
    private int p; //position in searching array
    
    public MineSweeper(){ 
        
        this(20,20);
        
    }
    
    public static void main (String args[]){
    	
    	MineSweeper minesweeper = new MineSweeper (30,30);
    	
    }
    
    public MineSweeper(int numCols, int numRows) //, int numMines
    {
        Field = new Status[numCols][numRows]; // create field array
        GameStatus = GStatus.FIRST; //set game to first click
        
        cols = numCols;
        rows = numRows;
        
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                //initialise all
                if (Math.random() < MINE_CHANCE){
                    Field[i][j] = Status.MINE;
                    minestotal ++;
                } else {
                    Field[i][j] = Status.CLEAR;
                }
            }
        }
        
        minesleft = minestotal; //number of mines in grid

        setup();
    }
    
    public MineSweeper(int numCols, int numRows, int numMines)
    {
        Field = new Status[numCols][numRows]; // create field array
        GameStatus = GStatus.FIRST; //set game to first click
        
        cols = numCols;
        rows = numRows;
        
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                //initialise all
                Field[i][j] = Status.CLEAR;
            }
        }
        
        minestotal = numMines; 
        for (int z = 0; z < numMines; z++){
            
            int i = (int) (double) (Math.random()*cols);
            int j = (int) (double) (Math.random()*rows);
            
            while (Field[i][j] == Status.MINE){
                i = (int) (double) (Math.random()*cols);
                j = (int) (double) (Math.random()*rows);
            }
            
            Field[i][j] = Status.MINE;
        }
        
        minesleft = minestotal; //number of mines in grid

        setup();
    }

    public MineSweeper(boolean[][] mineField){
        cols = mineField.length;    //  retrieve data from Tester Class
        rows = mineField[0].length; //
        
        Field = new Status[cols][rows];  // create field array
        GameStatus = GStatus.FIRST;
                
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                //initialise all
                if (mineField[i][j]) {
                    Field[i][j] = Status.MINE;
                    minestotal ++;
                } else {
                    Field[i][j] = Status.CLEAR;
                }
            }
        }
        minesleft = minestotal; //number of mines in grid
        setup();
    }    
    
    public void setup(){
        CANVAS_WIDTH = cols * SQUARE_SIZE + CANVAS_SIDES*2;
        CANVAS_HEIGHT = rows * SQUARE_SIZE + CANVAS_TOP + CANVAS_BOTTOM;

        sc = new SimpleCanvas("Alex's MineSweeper", CANVAS_WIDTH, CANVAS_HEIGHT);
        sc.addMouseListener(this);

        sc.setFont(defaultFont);
        
        blankSearch = false; //initial state

        drawGrid();
        
        //create array of 20 items
        firework_x = new double[NUM_PARTICLES];
        firework_y = new double[NUM_PARTICLES];
        
        firework_prev_x = new double[NUM_PARTICLES];
        firework_prev_y = new double[NUM_PARTICLES];
        
        firework_vel_x = new double[NUM_PARTICLES];
        firework_vel_y = new double[NUM_PARTICLES];
        
        fireworks_timer = new Timer(10, taskPerformer);
        timertimer = new Timer(600, nextminefirework);
    }

    public void mouseClicked(MouseEvent e) {
        
        int x = e.getX();
        int y = e.getY();
        int button = e.getButton();
        
        if(GameStatus == GStatus.OVER){
            if (fireworks_timer.isRunning()) fireworks_timer.stop();
            if (timertimer.isRunning()) timertimer.stop();
            newGame();
            return;
        }
        
        if (x<CANVAS_SIDES || x>CANVAS_WIDTH-CANVAS_SIDES || y<CANVAS_TOP || y>CANVAS_HEIGHT-CANVAS_BOTTOM){
            //ignore 
        } else {
            
            int xpos = (x-CANVAS_SIDES)/SQUARE_SIZE;
            int ypos = (y-CANVAS_TOP)/SQUARE_SIZE;
            
            if (button == 1) { 
                dig(xpos,ypos);
            } else if (button == 3){
                mark(xpos,ypos);
            }
            
            //sc.drawString (xpos + ", " + ypos, x, y); //temp - show grid location
        }
        
    }
    
    public void drawGrid() {
        
        //draw background if it's not white
        if(BACKGROUND_COLOUR != java.awt.Color.white){
            sc.setForegroundColour(BACKGROUND_COLOUR);
            for(int i = 0; i<=CANVAS_HEIGHT; i++){ //i = height
                sc.drawLine(0, i, CANVAS_WIDTH, i);
            }
        }
        
        //draw tiles
        sc.setForegroundColour(SQUARE_COLOUR);
        for(int i = CANVAS_TOP; i<=CANVAS_HEIGHT-CANVAS_BOTTOM; i++){ //i = height
            sc.drawLine(CANVAS_SIDES, i, CANVAS_WIDTH-CANVAS_SIDES, i);
        }
        
        //grid lines
        for(int i = CANVAS_TOP; i<=CANVAS_HEIGHT-CANVAS_BOTTOM; i+=SQUARE_SIZE){   //i = height
            sc.setForegroundColour(GRID_DARK_COLOUR);
            sc.drawLine(CANVAS_SIDES, i, CANVAS_WIDTH-CANVAS_SIDES, i);             //horizontal
            sc.setForegroundColour(GRID_LIGHT_COLOUR);
            sc.drawLine(CANVAS_SIDES, i+1, CANVAS_WIDTH-CANVAS_SIDES, i+1);         //horizontal
        }
        for(int i = CANVAS_SIDES; i<=CANVAS_WIDTH-CANVAS_SIDES; i+=SQUARE_SIZE){    //i = width
            sc.setForegroundColour(GRID_DARK_COLOUR);
            sc.drawLine(i, CANVAS_TOP, i, CANVAS_HEIGHT-CANVAS_BOTTOM);            //vertical
            sc.setForegroundColour(GRID_LIGHT_COLOUR);
            sc.drawLine(i+1, CANVAS_TOP, i+1, CANVAS_HEIGHT-CANVAS_BOTTOM);        //vertical            
        }
        
        updateMineCount();

    }
    
    public void showMines(boolean IfWon){ //true = win, false = lose.

        sc.setForegroundColour(MINE_COLOUR);
        q = 0;
        minearray_x = new int[cols*rows];
        minearray_y = new int[cols*rows];
        
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                
                if (IfWon){
                    
                    if(Field[i][j] == Status.MINE){
                         drawMark(i,j);
                         minesleft--;
                    }
                    
                } else {
                    
                    if(Field[i][j] == Status.MINE || Field[i][j] == Status.BAD_DIG){
                        //show mines
                        drawMine(i,j);
                        
                        minearray_x[q] = i;
                        minearray_y[q] = j;
                        q++;
                        //dofirework(i,j);
                    }
                    
                    if(Field[i][j] == Status.BAD_MARK){
                        //draw a cross
                        
                        sc.setForegroundColour(BAD_DIG_COLOUR);
                        for (int x=0; x <= 1; x++) { //fat cross
                            for (int y=0; y <= 1; y++) {
                                sc.drawLine(CANVAS_SIDES+i*SQUARE_SIZE+x, CANVAS_TOP+j*SQUARE_SIZE+y, CANVAS_SIDES+(i+1)*SQUARE_SIZE+x, CANVAS_TOP+(j+1)*SQUARE_SIZE+y); 
                                sc.drawLine(CANVAS_SIDES+(i+1)*SQUARE_SIZE+x, CANVAS_TOP+j*SQUARE_SIZE+y, CANVAS_SIDES+i*SQUARE_SIZE+x, CANVAS_TOP+(j+1)*SQUARE_SIZE+y); 
                            }
                        }
                    }
                    
                    if(Field[i][j] == Status.BAD_DIG){    
                        
                        //tile background
                        
                        /*
                        
                        sc.setForegroundColour(BAD_DIG_COLOUR); //draw background
                        for(int z = CANVAS_SIDES+i*SQUARE_SIZE+1; z < CANVAS_SIDES + (i+1)*SQUARE_SIZE; z++){
                            sc.drawLine(z,CANVAS_TOP+j*SQUARE_SIZE+1,z,CANVAS_TOP+(j+1)*SQUARE_SIZE-1);
                        }
                        
                        */
                       
                        dofirework(i,j); //instead of shaded background
                        
                        //draw mine
                        drawMine(i,j);
                    }
                    
                }
                
            }
        }
        
        if (!IfWon){
            timertimer.start();
            current_q = 0;
        }   
            
    }
    
    
    /* ====================== FIREWORK START ====================== */
    
    
        ActionListener nextminefirework = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
                current_q++;
                
                //random mine number
                int i = (int) (double) (Math.random()*q);
                
                fireworks_timer.stop();
                dofirework(minearray_x[i],minearray_y[i]);
                
                if (current_q == q-1) timertimer.stop();
                
                
            }
        };

    private void dofirework(int i, int j){
        
        firework_loc_x = (int) (double) (CANVAS_SIDES+(i+0.5)*SQUARE_SIZE);
        firework_loc_y = (int) (double) (CANVAS_TOP+(j+0.5)*SQUARE_SIZE);
            
        firework_reset();
        
        int temp = 0;
        
        fireworks_timer.start();
        
        if_red = (int)(Math.random()+0.5);
        if_green = (int)(Math.random()+0.5);
        if_blue = (int)(Math.random()+0.5);
        
        while (if_red + if_green + if_blue == 0 || if_red + if_green + if_blue == 3){
            if_red = (int)(Math.random()+0.5);
            if_green = (int)(Math.random()+0.5);
            if_blue = (int)(Math.random()+0.5);
        }
        rgb = 0;
    }
    
    //fireworks_timer action
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {

            int red = rgb * if_red;
            int blue = rgb * if_blue;
            int green = rgb * if_green;
            
            sc.setForegroundColour(new java.awt.Color(red, green, blue)); 
            firework_refresh(rgb/50);
            
            rgb +=5;
            if (rgb >= 255) fireworks_timer.stop();

        }
    };

    private void firework_reset(){
        for (int i = 0; i < NUM_PARTICLES; i++){
            firework_x[i] = firework_loc_x;
            firework_y[i] = firework_loc_y;
            
            //firework_vel_y[i] = dir * Math.sqrt( VELOCITY*VELOCITY - firework_vel_x[i]*firework_vel_x[i]);
                firework_vel_y[i] = (Math.random()-0.5)*VELOCITY;
                firework_vel_x[i] = (Math.random()-0.5)*VELOCITY;
            
            while (firework_vel_y[i]*firework_vel_y[i] + firework_vel_x[i]*firework_vel_x[i] > (VELOCITY/2)*(VELOCITY/2)){
                firework_vel_y[i] = (Math.random()-0.5)*VELOCITY;
                firework_vel_x[i] = (Math.random()-0.5)*VELOCITY;
            }
            
            firework_prev_x[i] = firework_x[i];
            firework_prev_y[i] = firework_y[i];
        }
    }
    
    public void firework_refresh(int grav)
    {
        //each frame
        for (int i = 0; i < NUM_PARTICLES; i++){
            sc.drawLine((int) firework_x[i], (int) firework_y[i], (int) firework_prev_x[i], (int) firework_prev_y[i]);
            
            firework_prev_x[i] = firework_x[i];
            firework_prev_y[i] = firework_y[i];
            
            firework_x[i] += firework_vel_x[i];
            firework_y[i] += firework_vel_y[i] + grav;
        }
    }
    
    
    /* ====================== FIREWORK END ====================== */
    
    
    
    public void showDig(int x, int y){
        sc.setForegroundColour(DUG_COLOUR); //draw background
        for(int z = CANVAS_SIDES+x*SQUARE_SIZE+1; z < CANVAS_SIDES + (x+1)*SQUARE_SIZE; z++){
            sc.drawLine(z,CANVAS_TOP+y*SQUARE_SIZE+1,z,CANVAS_TOP+(y+1)*SQUARE_SIZE-1);
        }
        
        int tempNearby = nearbyMines(x,y); //so its only called once
        
        if (tempNearby == 0){
            zeroArea(x,y);
        } else {
            //set text colour depending on number
            switch (tempNearby){
                //will never be 0
                case 1: sc.setForegroundColour(java.awt.Color.blue);         break;
                case 2: sc.setForegroundColour(new java.awt.Color(0,128,0)); break;
                case 3: sc.setForegroundColour(java.awt.Color.red);          break;
                case 4: sc.setForegroundColour(new java.awt.Color(0,0,128)); break;           
                case 5: sc.setForegroundColour(new java.awt.Color(128,0,0)); break;
                default: sc.setForegroundColour(java.awt.Color.black);
            }
            
            //write number in centre of tile
            sc.drawString(""+tempNearby, CANVAS_SIDES + x*SQUARE_SIZE + SQUARE_SIZE/3, CANVAS_TOP + y*SQUARE_SIZE + SQUARE_SIZE*2/3);
        }
    }
    
    public int nearbyMines(int x, int y){
        int nearbyMines = 0;
        for (int i = x-1; i <= x+1; i++){
            for (int j = y-1; j <= y+1; j++){
                if (positionExists(i,j)){
                    if(Field[i][j] == Status.MINE || Field[i][j] == Status.GOOD_MARK){
                        nearbyMines++; //increment count
                    }
                }
            }
        }
        return nearbyMines;
    }
    
    public boolean positionExists(int x, int y){
        return (x >= 0 && y >= 0 && x < cols && y < rows);
    }
    
    public void zeroArea(int x, int y){
        if(blankSearch == false){
            //set size of blank tile array to be max possible
            blankX = new int[cols*rows];
            blankY = new int[cols*rows];
            p = 0;
                    
            //initialise array to -1 values
            for (int i = 0; i<cols*rows; i++){
                blankX[i] = -1;
                blankY[i] = -1;
            }
            blankSearch = true;
        }
        
        addToArray(x,y);
        
        for (int q = 0; q < p+1; q++){
            search(x,y);
            
            x = blankX[q];
            y = blankY[q];
        }
        
        for (int q = 0; q < p; q++){
            x = blankX[q];
            y = blankY[q];
            
            digAround(x,y);
                    
        }
        
        blankSearch = false;
    }
    
    public void digAround(int x, int y){
        for (int i = x-1; i <= x+1; i++){ 
            for (int j = y-1; j <= y+1; j++){
                if(positionExists(i,j) && (Field[i][j] != Status.GOOD_MARK && Field[i][j] != Status.BAD_MARK) ){
                    sc.setForegroundColour(DUG_COLOUR); //draw background
                    for(int z = CANVAS_SIDES+i*SQUARE_SIZE+1; z < CANVAS_SIDES + (i+1)*SQUARE_SIZE; z++){
                        sc.drawLine(z,CANVAS_TOP+j*SQUARE_SIZE+1,z,CANVAS_TOP+(j+1)*SQUARE_SIZE-1);
                    }
                    Field[i][j] = Status.DUG;
                                        
                    int tempNearby = nearbyMines(i,j); //so its only called once

                    if (tempNearby == 0){
                        //do nothing - already searched everywhere
                    } else {
                        //set text colour depending on number
                        switch (tempNearby){
                            //will never be 0
                            case 1: sc.setForegroundColour(java.awt.Color.blue);         break;
                            case 2: sc.setForegroundColour(new java.awt.Color(0,128,0)); break;
                            case 3: sc.setForegroundColour(java.awt.Color.red);          break;
                            case 4: sc.setForegroundColour(new java.awt.Color(0,0,128)); break;           
                            case 5: sc.setForegroundColour(new java.awt.Color(128,0,0)); break;
                            default: sc.setForegroundColour(java.awt.Color.black);
                        }
                        
                        //write number in centre of tile
                        sc.drawString(""+tempNearby, CANVAS_SIDES + i*SQUARE_SIZE + SQUARE_SIZE/3, CANVAS_TOP + j*SQUARE_SIZE + SQUARE_SIZE*2/3);
                    }
                }
            }
        }
    }
    
    
    public void search (int x, int y){ //search 1 tile around xy
        for (int i = x-1; i <= x+1; i++){ 
            for (int j = y-1; j <= y+1; j++){
                addToArray(i,j);
            }
        }
    }
    
    public void addToArray(int x, int y){
        
        if (positionExists(x,y) && nearbyMines(x,y)==0 && (Field[x][y]==Status.CLEAR || Field[x][y]==Status.DUG) && !(inArray(x,y))){
            blankX[p] = x;
            blankY[p] = y;
            p++;
        }
    }
    
    public boolean inArray (int x, int y){ //returns true if x & y is in array blankX & Y
        
        for (int i = 0; i < p; i++) {
            if(blankX[i] == x && blankY[i] == y){
                return true;
            }
        }
        return false;
    }       
    
    public void dig(int x, int y) {
        if(positionExists(x,y)){
            if (GameStatus == GStatus.FIRST){
                //first click - if it's a mine move the bomb to a random coordinate
                
                if (Field[x][y] == Status.MINE){
                    
                    int i = (int) (Math.random()*cols); //initial coords
                    int j = (int) (Math.random()*rows); 
                    int loopprevent = 0;
                    while(Field[i][j] == Status.MINE){ //find the next non-mine cell
                        i = (int) (Math.random()*cols);
                        j = (int) (Math.random()*rows);
                        if (loopprevent > 1000){break;}
                    }
                    Field[i][j] = Status.MINE;
                    Field[x][y] = Status.DUG;
                    showDig(x,y);
                }
                
                GameStatus = GStatus.PLAYING; //set game status to playing
                
            } else if (GameStatus == GStatus.OVER){
                //if game is over - mouseclick starts new game? 
                //ignore            
            } 
            
            if (GameStatus == GStatus.PLAYING) { 
                //playing normally
                
                if(Field[x][y] == Status.MINE) { //click on mine
                    GameStatus = GStatus.OVER; //game over
                    Field[x][y] = Status.BAD_DIG;
                    drawMessage("Game Over - You Lose");
                    showMines(false);
                } else if(Field[x][y] == Status.CLEAR){ //click on clear
                    Field[x][y] = Status.DUG;
                    showDig(x,y);
                    checkWin();
                }
            }
        } else {
            //error
        }
    }
    
    public void drawMessage(String message){
        sc.setFont(messageFont);
        sc.setForegroundColour(FOREGROUND_COLOUR);
        sc.drawString(message, CANVAS_WIDTH/2 - 60, CANVAS_HEIGHT-CANVAS_BOTTOM/2+5); //60 works for all the strings sent at font size 12
        sc.setFont(defaultFont);
    }
    
    public void mark(int x, int y) {   
        if(Field[x][y] == Status.GOOD_MARK){
            Field[x][y] = Status.MINE;
            minesleft++;
            clearMark(x,y);
            
        } else if(Field[x][y] == Status.BAD_MARK){
            Field[x][y] = Status.CLEAR;
            minesleft++;
            clearMark(x,y);
            
        } else if(Field[x][y] == Status.CLEAR){
            Field[x][y] = Status.BAD_MARK;
            drawMark(x,y);
            minesleft--;
            
        } else if(Field[x][y] == Status.MINE){
            Field[x][y] = Status.GOOD_MARK;
            drawMark(x,y);
            minesleft--;
        }
        
        checkWin();
    }

    private void updateMineCount(){
        
        //mine counter font
        sc.setFont(mineCountFont);
        
        //set top area to background colour
        sc.setForegroundColour(BACKGROUND_COLOUR);
        for(int i = 0; i<CANVAS_TOP; i++){
            sc.drawLine(0, i, CANVAS_WIDTH, i);
        }
        
        sc.setForegroundColour(FOREGROUND_COLOUR);
        //show numer of flags remaining
        sc.drawString("" + minesleft, CANVAS_SIDES, CANVAS_TOP/2+10);
        
        //set to default font
        sc.setFont(defaultFont);
    }
    
    private void drawMark(int x, int y){
        //sc.drawString("F", CANVAS_SIDES + x*SQUARE_SIZE + SQUARE_SIZE/3, CANVAS_TOP + y*SQUARE_SIZE + SQUARE_SIZE*2/3);
        drawflag(x,y);
    }
    
    private void drawMine(int x, int y){
        sc.setForegroundColour(MINE_COLOUR);
        //sc.drawString("M", 25 + x*SQUARE_SIZE + SQUARE_SIZE/3, 50 + y*SQUARE_SIZE + SQUARE_SIZE*2/3);
        drawcircle(CANVAS_SIDES + x*SQUARE_SIZE + SQUARE_SIZE/2, CANVAS_TOP + y*SQUARE_SIZE + SQUARE_SIZE/2, SQUARE_SIZE/4);
    }
    
    private void clearMark(int i, int j){
        sc.setForegroundColour(SQUARE_COLOUR);
        //sc.drawString("F", CANVAS_SIDES + x*SQUARE_SIZE + SQUARE_SIZE/3, CANVAS_TOP + y*SQUARE_SIZE + SQUARE_SIZE*2/3);
        
        int x = CANVAS_SIDES + i * SQUARE_SIZE;
        int y = CANVAS_TOP + j * SQUARE_SIZE;
        
        for (int w = x + (int) (0.1*SQUARE_SIZE); w <= x + (int) (0.9*SQUARE_SIZE); w++){
            sc.drawLine(w, y + (int) (0.1*SQUARE_SIZE), w, y + (int) (0.9*SQUARE_SIZE));
        }
    }

    public void drawcircle(int x, int y, int size){
        for (int i=-size; i<=size; i++){
            int dist = (int) Math.sqrt((size*size)-(i*i));
            sc.drawLine(x-dist, y+i, x+dist, y+i);
        }
    }
    
    public void drawflag(int i, int j){    
        int x = CANVAS_SIDES + i * SQUARE_SIZE;
        int y = CANVAS_TOP + j * SQUARE_SIZE;
        
        sc.setForegroundColour(FLAG_BASE_COLOUR);
        
        for (int w = -1; w <= 1; w++) {
            sc.drawLine((int) (x + 0.5*SQUARE_SIZE)+w, (int) (y + 0.2*SQUARE_SIZE), (int) (x + 0.5*SQUARE_SIZE)+w, (int) (y + 0.8*SQUARE_SIZE)); //pole
            sc.drawLine((int) (x + 0.2*SQUARE_SIZE)-w, (int) (y + 0.8*SQUARE_SIZE)+w, (int) (x + 0.8*SQUARE_SIZE)+w, (int) (y + 0.8*SQUARE_SIZE)+w); //base
        }
        
        sc.setForegroundColour(FLAG_TOP_COLOUR);
                
        for (int w = 0; w <= (int) (0.3*SQUARE_SIZE); w++){
            sc.drawLine((int) (x + 0.2*SQUARE_SIZE), (int) (y + 0.35*SQUARE_SIZE), (int) (x + 0.5*SQUARE_SIZE)+1, (int) (y + 0.2*SQUARE_SIZE)+w); //flag top
            sc.drawLine((int) (x + 0.2*SQUARE_SIZE), (int) (y + 0.35*SQUARE_SIZE), (int) (x + 0.5*SQUARE_SIZE)+1, (int) (y + 0.5*SQUARE_SIZE)-w); //flag bottom
        }
    }
        
    private void checkWin(){

        int clearCount = 0; 
        int badCount = 0; 
        
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                if(Field[i][j] == Status.CLEAR){
                    clearCount++;
                }
                if(Field[i][j] == Status.BAD_MARK){
                    badCount++;
                }
            }
        }

        if(clearCount == 0 && badCount==0){         //win condition - all dug except mines
            GameStatus = GStatus.OVER;
            drawMessage("Game Over - You Win");
            showMines(true);
        }
        
        updateMineCount();
    }
    
    public void newGame(){
        p = 0;
        minestotal = 0;
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                //initialise all
                if (Math.random() < MINE_CHANCE){
                    Field[i][j] = Status.MINE;
                    minestotal ++;
                } else {
                    Field[i][j] = Status.CLEAR;
                }
            }
        }
        
        minesleft = minestotal; //number of mines in grid
        GameStatus = GStatus.FIRST;
        drawGrid();
    }
    
    // Required for compiler
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
}
