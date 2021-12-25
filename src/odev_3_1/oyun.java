
package odev_3_1;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class oyun extends JPanel implements KeyListener,ActionListener {
        Timer timer = new Timer(3,this);
        private BufferedImage image,image2;
        float alpha =1f;
       float alpha2 =0f;
          double aci=40,hiz=60;
          double xSpeed,ySpeed;
          double time, delayy = 0.09 ,G=9.8 ;
          double startX ;
          double startY ;
          double ballX, ballY;
         int hedefX;
        public oyun(){
            int k;
             Random random = new Random();
            do{
            k= Math.abs((random.nextInt()))%1200;
            } while(k<300);
            hedefX=k;
              System.out.println("Konum="+hedefX);
            ballX=startX = 90;
             ballY =startY = 670;
             
        
        try {
            image=ImageIO.read(new FileImageInputStream(new File("birds.png")));
        } catch (IOException ex) {
            Logger.getLogger(oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            image2=ImageIO.read(new FileImageInputStream(new File("sapan.jpg")));
        } catch (IOException ex) {
            Logger.getLogger(oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                 }
   
       
     public void random(){
             int k;
             Random random = new Random();
            do{
            k= Math.abs((random.nextInt()))%1200;
            } while(k<300);
            hedefX=k;
             System.out.println("Konum="+hedefX);
           
        }
    @Override
    public void paint(Graphics g) {
        
        super.paint(g); 
      
        ImageIcon i= new ImageIcon("image.jpg");
        i.paintIcon(this, g, 0, 0);
        Graphics2D g2d = (Graphics2D) g.create();
        
         g2d.drawImage(image,(int)ballX,(int)ballY,image.getWidth()/18,image.getHeight()/18,this);
          
         g2d.drawImage(image2,10,680,image.getWidth()/12,image.getHeight()/12,this);
        
            g2d.setColor(Color.blue);
            
            AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alcom);
            g2d.fillRect(hedefX, 680, 110, 80);
           
             g2d.setColor(Color.black);
            AlphaComposite alcom2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha2);
            g2d.setComposite(alcom2);
            g2d.fillOval((hedefX), 720, 40, 40);
             g2d.dispose();
          
    }
  
    @Override
    public void repaint() {
        super.repaint(); 
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
       
       if(c== KeyEvent.VK_LEFT){
           hiz-=5;
           
       }
       else if(c== KeyEvent.VK_RIGHT){
           hiz+=5;
           System.out.print("Hız="+hiz+ ", ");
       }
        else if(c== KeyEvent.VK_DOWN){
           aci-=5;
       }
        else if(c== KeyEvent.VK_UP){
           aci+=5;
           System.out.print("Hız="+aci+ ", ");
       }
        else if(c== KeyEvent.VK_SPACE){
            System.out.println();
        timer.start();
        
       }
       else if(c== KeyEvent.VK_CONTROL){
        timer.stop();
        ballX= startX = 90;
        ballY =  startY = 670;
         time=0.07;
          aci=40;
          hiz=60;
          repaint();
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
       xSpeed = hiz * Math.cos(aci * (Math.PI / 180));
       ySpeed = hiz * Math.sin(aci * (Math.PI / 180));
       ballX = startX + (xSpeed * time);
       ballY = startY - ((ySpeed *time)-(0.5 *G * Math.pow(time, 2)));
       time += delayy; 
        repaint();
        if(ballX>hedefX &&ballX <1290 && ballY>700 && ballY<800){
             alpha =0f;
             alpha2=1f;
            String message="Tebrikler";
            JOptionPane.showMessageDialog(this, message);
             timer.stop();
             ballX= startX = 90;
             ballY =  startY = 670;
             aci=40;
             hiz=60;
              time=0.09;
             this.random();
            repaint();
            timer.stop();
             alpha =1f;
             alpha2=0f;
     
        }
        else if(ballX>1300 || ballY>800 ){
        ballX= startX = 90;
        ballY =  startY = 670;
         time=0.09;
          aci=40;
          hiz=60;
          repaint();
          timer.stop();
         
        }
        
    }
}
