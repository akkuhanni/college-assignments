import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

class MyCardDemo1 extends javax.swing.JFrame  implements ActionListener
{
	
	Button b3= new Button("Next");
	Panel listCards;
	Button b4= new Button("Back");
    Button	jButton1=new Button("choose");
	 javax.swing.JLabel label1;
     javax.swing.JLabel note;
     javax.swing.JLabel warning;
	CardLayout cardLO;
	int password[][]= new int[10][2];
	static int i=0,j=0;
	
	MyCardDemo1()
	{
	
		cardLO=new CardLayout();
		listCards=new Panel();
		listCards.setLayout(cardLO);
		b3.addActionListener(this);
		Panel p1=new Panel();
		p1.add(b3);
		p1.add(jButton1);
		p1.add(label1);
		p1.add(note);
		p1.add(warning);
		note.setVisible(false);
		//p1.warning.setVisible(false);
		//Choice

		Panel p2=new Panel();
		p2.add(b4);
		b4.addActionListener(this);
		//add cards to deck

		listCards.add(p1,"list");
		listCards.add(p2,"choice");

		add(listCards);
		
		setVisible(true);
		setSize(400,400);
		setLayout(new FlowLayout());

	}

	public void actionPerformed(ActionEvent evt)
	{
		String str=evt.getActionCommand();
		if(str.equals("Next"))
		{
		  cardLO.show(listCards,"choice");
		}
		else if(str.equals("choose"))
		{
			JFileChooser jfc= new JFileChooser();
         FileFilter imgfilter = new FileNameExtensionFilter(
        "Image files", ImageIO.getReaderFileSuffixes());
        jfc.showOpenDialog(this);
        File f=jfc.getSelectedFile();
        String imgpath= f.getAbsolutePath();
        
        BufferedImage bi = null;
        try {
        bi = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("No image selected! ");
        //e.printStackTrace();
        }           
        ImageIcon icon = new ImageIcon(bi.getScaledInstance(label1.getWidth(), 
                label1.getHeight(), BufferedImage.SCALE_SMOOTH)); 
        //JLabel label1 = new JLabel(); 
        label1.setIcon(icon);
        label1.setVisible(true);
        note.setVisible(true); 
        label1.setOpaque(false); 
        label1.addMouseListener(new MouseAdapter() 
         {  
             @Override
            public void mouseClicked(MouseEvent e ) throws ArrayIndexOutOfBoundsException
         {   try{
             System.out.println("mouseClicked");
             
             password[j][0]=e.getX();
             password[j][1]=e.getY();
             System.out.println(password[j][0]+","+password[j][1]);
             j++;
            }
             
             catch(ArrayIndexOutOfBoundsException ae)
             {  warning.setText("You have exceeded Password limit!"); 
                warning.setVisible(true);
                b3.setVisible(false);
             }
             if(password.length > 5)
             {     
                 b3.setVisible(true);
                 
             }
             
         } 
         });
		
		
		
		
		
		
		}


		else if(str.equals("Back"))

		{

			cardLO.show(listCards,"list");
		}

	}

	public static void main(String args[])
	{
		MyCardDemo1 mcd=new MyCardDemo1();

	}	


}