import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
	 
	 
	/**
	* Done Is the Nobbin character that is one of the antagonists in the game.
	*
	* @author weny, peruman.
	*         Created Oct 31, 2014.
	*/
	public class Nobbin extends AbstractCharacter {
	     private Color color1 = Color.pink;
		private BufferedImage img1;
		private BufferedImage img2;
		private boolean change= true; 
		private int count;
		
	     /**
	     * Constructs the Nobbin in the game
	     * @param x_pos
	     * @param y_pos
	     * @param blocks
	     */
	     public Nobbin(int x, int y) {
	    	 	super();
	           this.x = x;
	           this.y = y;
	           try {
	   			this.img1 = ImageIO.read(new File("src/Nobbin.png"));
	   			this.img2 = ImageIO.read(new File("src/hobbin.png"));
	   		} catch (IOException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		};
	           
	     }
	   
	     /**
	      * Draws the Nobbin character onto the screen
	      */
	     @Override
	     public void draw(Graphics2D g) {
	         if(this.change){
	           g.drawImage(this.img1, this.x, this.y, 40, 40, null);}
	         else{
	           g.drawImage(this.img2, this.x, this.y, 40, 40, null);
	         }
	     }
	     
	     /**
	      * Returns the Buffered Image used as the likeness for the Nobbin
	      * @return
	      */
	     public boolean getimg() {
		 		if (this.img1 == null) return true;
		 		else return false;
		 	}
	    
	     /**
	      * Checks the direction of movement for the Nobbin so it can track and AI and not hit blocks.
	      * @param blockList
	      * @param up
	      * @param left
	      * @return
	      */
	     public boolean checkDirect(int[][] blockList,int up,int left){
	    	 
	    	 if(this.x/40+left<=19&&this.y/40+up<=19&&this.x/40+left>=0&&this.y/40+up>=0){
	    		if(blockList[this.x/40+1+left][this.y/40+up]==0){
	    			 return true;
	    		}else{
	    		 return false;
	    		}
	    	 }
	    	 return false;
	     }
	     
	     /**
	      * Used to control the AI of the Nobbin
	      */
	     public void check(){
	    	 if(this.count>500){
	    		 this.count =0;
	    		 this.change = !this.change;
	    	 }
	    	 else{
	    		 this.count++;
	    	 }
	     }
	    
	     /**
	      * Used to actually move the Nobbin around while ensuring it doesn't violate the rules of the game by digging up
	      * a block.
	      * @param blockList
	      */
	     public void move(int[][] blockList){
	    	 this.check();
	    	 ArrayList<Boolean> truthValue = new ArrayList<Boolean>();
	    	 
	    	 int countForTrue = 0;
	    	 if (this.x/40 <18){
		    	 truthValue.add(checkDirect(blockList,-1,0));
		    	 truthValue.add(checkDirect(blockList,0,1));
		    	 truthValue.add(checkDirect(blockList,0,-1));
		    	 truthValue.add(checkDirect(blockList,1,0));
	    	 }
	    	 else{
	    		 if(checkDirect(blockList,1,0)){
		    		 this.y_Velocity=1;
		    		 this.x_Veloctiy=0;
		    	 }
		    	 else if(checkDirect(blockList,0,-1)){
		    		 this.x_Veloctiy=-1;
		    		 this.y_Velocity=0;
		    	 }
		    	 else if(checkDirect(blockList,0,1)){
		    		 this.x_Veloctiy=1;
		    		 this.y_Velocity=0;
		    	 }
		    	 else if(checkDirect(blockList,-1,0)){
		    		 this.y_Velocity=-1; 
		    		 this.x_Veloctiy=0;
		    	 }
	    	 }
	    	 
	    	 
	    	 for (int i = 0; i < truthValue.size(); i++){
	    		 if (truthValue.get(i)==true){
	    			 countForTrue += 1;
	    		 }
	    	 }
	    	 if (countForTrue == 3 || countForTrue == 4){
		    	 if(checkDirect(blockList,1,0)){
		    		 this.y_Velocity=1;
		    		 this.x_Veloctiy=0;
		    	 }
		    	 else if(checkDirect(blockList,0,-1)){
		    		 this.x_Veloctiy=-1;
		    		 this.y_Velocity=0;
		    	 }
		    	 else if(checkDirect(blockList,0,1)){
		    		 this.x_Veloctiy=1;
		    		 this.y_Velocity=0;
		    	 }
		    	 else if(checkDirect(blockList,-1,0)){
		    		 this.y_Velocity=-1; 
		    		 this.x_Veloctiy=0;
		    	 }
	    	 }
	    	 else if (countForTrue == 2){
	    		 if ((truthValue.get(1) == true && truthValue.get(3) == true)){
	    			 if(checkDirect(blockList,1,0)){
			    		 this.y_Velocity=1;
			    		 this.x_Veloctiy=0;
			    	 }
			    	 else if(checkDirect(blockList,0,-1)){
			    		 this.x_Veloctiy=-1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,0,1)){
			    		 this.x_Veloctiy=1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,-1,0)){
			    		 this.y_Velocity=-1; 
			    		 this.x_Veloctiy=0;
			    	 } 
	    			 
	    		 }
	    		 else if((truthValue.get(2) == true && truthValue.get(3) == true)){
	    			 if(checkDirect(blockList,-1,0)){
			    		 this.y_Velocity=-1; 
			    		 this.x_Veloctiy=0;
			    	 } 
	    			 else if(checkDirect(blockList,0,-1)){
			    		 this.x_Veloctiy=-1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,1,0)){
			    		 this.y_Velocity=1;
			    		 this.x_Veloctiy=0;
			    	 }

			    	 else if(checkDirect(blockList,0,1)){
			    		 this.x_Veloctiy=1;
			    		 this.y_Velocity=0;
			    	 }
	    			 
	    		 }
	    		 else if ((truthValue.get(1) == true && truthValue.get(0) == true)){
	    			 if(checkDirect(blockList,-1,0)){
			    		 this.y_Velocity=-1; 
			    		 this.x_Veloctiy=0;
			    	 } 
	    			 
			    	 else if(checkDirect(blockList,0,-1)){
			    		 this.x_Veloctiy=-1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,0,1)){
			    		 this.x_Veloctiy=1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,1,0)){
			    		 this.y_Velocity=1;
			    		 this.x_Veloctiy=0;
			    	 }
	    		 }
	    		 else if ((truthValue.get(0) == true && truthValue.get(2) == true)){
	    			 if(checkDirect(blockList,1,0)){
			    		 this.y_Velocity=1;
			    		 this.x_Veloctiy=0;
			    	 }
			    	 else if(checkDirect(blockList,0,-1)){
			    		 this.x_Veloctiy=-1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,0,1)){
			    		 this.x_Veloctiy=1;
			    		 this.y_Velocity=0;
			    	 }
			    	 else if(checkDirect(blockList,-1,0)){
			    		 this.y_Velocity=-1; 
			    		 this.x_Veloctiy=0;
			    	 }  
	    		 }
	    	 }
	    	 
	    	 else if (countForTrue == 1){
	    		 if(checkDirect(blockList,1,0)){
		    		 this.y_Velocity=1;
		    		 this.x_Veloctiy=0;
		    	 }
		    	 if(checkDirect(blockList,0,-1)){
		    		 this.x_Veloctiy=-1;
		    		 this.y_Velocity=0;
		    	 }
		    	 if(checkDirect(blockList,0,1)){
		    		 this.x_Veloctiy=1;
		    		 this.y_Velocity=0;
		    	 }
		    	 if(checkDirect(blockList,-1,0)){
		    		 this.y_Velocity=-1; 
		    		 this.x_Veloctiy=0;
		    	 } 
	    	 }
	    	 
	     }
	     
	     /**
	      * Finally updates the position of the Nobbin
	      */
	     public void updatePosition(){
	           this.x+=this.x_Veloctiy;
	           this.y+=this.y_Velocity;
	          
	     }
	     
	     /**
	      * Is used to set the Nobbin anywhere on the game screen
	      */
	     public void setPosition(int x,int y){
	           this.x=x;
	           this.y=y;
	     }
	}
