/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojfinal;

import ae.java.awt.dnd.DnDConstants;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileView;
//import java.io.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;


/**
 *
 * @author Karthik
 */
 class CoOrdinate {
    int x;
    int y;
    
    CoOrdinate(int x, int y){
        this.x=x;
        this.y=y;
    }
    
}


public class MiniProjFinal extends javax.swing.JFrame {

    File f,plf,fimgpath,ftemp,fregistration;
    String imgpath;
    CoOrdinate setP[]= new CoOrdinate[10];
    CoOrdinate loginP[]=new CoOrdinate[10];
    CoOrdinate checkP[]=new CoOrdinate[10];
    String username,email,fusername;
    boolean registered=false;
    BufferedImage bi;
    ImageIcon icon;
    int m,pLength=0,pLength1=0,mugvar=0,valid=0,loggedIn=0;
    private  String OTP1;
    
    private static final Pattern usrNamePtrn = Pattern.compile("^[a-zA-Z0-9_-]+{6,14}$");
    
    private static final Pattern emailPtrn = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
     
    public static boolean validateUserName(String userName){
         
        Matcher mtch = usrNamePtrn.matcher(userName);
        return mtch.matches();
    }
    
    public static boolean validateEmail(String Email){
         
        Matcher mtch = emailPtrn.matcher(Email);
        return mtch.matches();
    }
            
                
        
            
    
    /**
     * Creates new form MiniProjFinal
     */
    public MiniProjFinal() {
        initComponents();
        note.setVisible(false);
        Next.setVisible(false);
        Confirm.setVisible(false);
        //LoginButton.setVisible(false);
        resetPassword.setVisible(false);
        changeEID.setVisible(false);
        warning1.setVisible(false);
        warning.setVisible(false);
    }

    public static String generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyz"
                     + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "0123456789";

        final int PW_LENGTH = 7;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        return pass.toString();
    }
    
    
    public void DragDrop ()
    {
        dragdrop.setDropTarget (new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt){
                try{
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles=(List<File>) evt.getTransferable().
                            getTransferData(DataFlavor.javaFileListFlavor);
                    for(File  fe : droppedFiles){
                        System.out.println("dropped");
                        String efilepath,efilename;
                        efilepath= fe.getAbsolutePath();
                        miniprojfinal.AESFileEncryption.encrypt(efilepath,"/home/akku/NetBeansProjects/MiniProjFinal(k)/encrypted_files");
                        efilename=fe.getName();
                        JOptionPane.showMessageDialog(null,efilename+" has been successfully encrypted!" );
                        writeToLog(efilename,'e');
                        CardLayout card=(CardLayout)MainPanel.getLayout();
                        card.show(MainPanel,"Encrypt");
                    }
                }
                    catch(Exception e)
                            {
                            System.out.println(e);
                            }
     
            }
    });
    }
    
    
    public  void loadLogin()
    {
        String s1="/home/akku/NetBeansProjects/MiniProjFinal(k)/temp.txt";
        try
        {
           if(checkFile(s1))
        {
           BufferedReader br = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal(k)/imgname.txt"));     
           String s=br.readLine();
           //System.out.println(s);
           try
           {
               f=new File(s);
               imgpath= f.getAbsolutePath();
              // System.out.println(imgpath);
               
           }
           catch(Exception e)
                   {
                       System.out.println(e);
                   }
           
           try{
        Scanner inFile1 = new Scanner(new File("temp.txt")).useDelimiter("\\s*,\\s*");
        java.util.List<Integer> temps = new ArrayList<Integer>();
        while (inFile1.hasNext()) {
        int token1 = inFile1.nextInt();
        temps.add(token1);
        System.out.println("t="+temps);
        }
        //System.out.println("here");
        inFile1.close();
        Integer[] tempsArray = temps.toArray(new Integer[0]);

         for (Integer x : tempsArray) {
          System.out.println(s);
         }
         int m=0;
          for(int j=0;j<10;j=j+2)
          {
             
              loginP[m]=new CoOrdinate(tempsArray[j],tempsArray[j+1]);
              m++;
              
          }       
        }
        catch(Exception ee)
        {
         System.out.println(ee);   
        }
       try {
            BufferedReader brP = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal(k)/passlength.txt"));     
           pLength=Integer.parseInt(brP.readLine());
        } catch(IOException ioe)
       {
        System.out.println("file not found exception");
       }
           
           CardLayout card=(CardLayout)MainPanel.getLayout();
           card.show(MainPanel,"Login");
           LoadImage(ImageLabel2);
 
        }
       else
           {
            System.out.println("in else");
            CardLayout card=(CardLayout)MainPanel.getLayout();
            card.show(MainPanel,"Registration");   
           }
       }
       catch(IOException ioe)
       {
        System.out.println("file not found exception");
       }
        
    }
   
        
  
    
    public boolean checkFile(String s) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(s));     
         if (br.readLine() == null) {
        System.out.println("file empty");
        return(false);
    
    }
         else
            return(true);
    }
    
    
    public void writeToLog (String fn, char type){
        // 1) create a java calendar instance
             String content;
             Calendar calendar = Calendar.getInstance();
 
             // 2) get a java.util.Date from the calendar instance.
             //    this date will represent the current instant, or "now".
             java.util.Date now = calendar.getTime();
                        
             if(type=='e')
                {
                    content = fn+" was encrypted at "+now+"\n";
                    System.out.println(content);
                }
             else
                {
                    content = fn+" was decrypted at "+now+"\n";
                    System.out.println(content);
                }
        try {
                        

		File lf = new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/logFile.txt");
                
		// if file doesnt exists, then create it
                    
		if (!lf.exists()) {
                    lf.createNewFile();
                    lf.setReadOnly();
		}
		lf.setWritable(true);
                BufferedWriter bw = new BufferedWriter(new FileWriter(lf,true));
		bw.write(content);
                bw.close();
                lf.setWritable(false);
                
		System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
            
    
    
    public  void write (String filename, CoOrdinate[]x) throws IOException{
    BufferedWriter outputWriter = null;
    outputWriter = new BufferedWriter(new FileWriter(filename));
    for (int sd = 0; sd<=pLength-1; sd++) {
    // Maybe:
      System.out.println(x[sd].x+","+x[sd].y);
     // outputWriter.write(x[sd].x1+","+x[sd].y1);
    //Or:
    outputWriter.write(Integer.toString(x[sd].x));
    outputWriter.write(",");
    outputWriter.write(Integer.toString(x[sd].y));
    if(sd<pLength)
    outputWriter.write(",");
    //outputWriter.newLine();
  }
  outputWriter.flush();  
  outputWriter.close();  
}
  
    
   private void LoadImage(final javax.swing.JLabel imglabel1){
       
       // repaint();
        m=0;
        bi = null;
        try {
        bi = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("No image selected! ");
        //e.printStackTrace();
        }           
        
        icon = new ImageIcon(bi.getScaledInstance(imglabel1.getWidth(), 
                imglabel1.getHeight(), BufferedImage.SCALE_SMOOTH)); 
         imglabel1.setIcon(icon);
         imglabel1.setVisible(true);
         note.setVisible(true); 
         imglabel1.setOpaque(false); 
         System.out.println("image loaded");
         imglabel1.addMouseListener(new MouseAdapter() 
         {  
             @Override
            public void mouseClicked(MouseEvent e) throws ArrayIndexOutOfBoundsException
         {   
             try{
                    
                    checkP[m]=new CoOrdinate(e.getX(),e.getY());
                    System.out.println("mouseClicked");
                    System.out.println(m);
                    Graphics g = imglabel1.getGraphics();
                    g.setColor(Color.red); //Setting color to red
                    g.fillOval(checkP[m].x,checkP[m].y, 10, 10); //Drawing the circle/point
                    g.drawString(Integer.toString(m+1), checkP[m].x, checkP[m].y );
                    g.dispose();
                    m++;
                    if(m==5){
                        Next.setVisible(true);
                        Confirm.setVisible(true);
                    }
                }
             
             catch(ArrayIndexOutOfBoundsException ae)
             {  warning.setText("You have exceeded Password limit!"); 
                warning.setVisible(true);
             }   
            // pLength1=m;
             File lf = new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/temp.txt");
                
		// if file doesnt exists, then create it
                    
		if (lf.exists()) {
                    pLength1=m;
                    
		}
                else
                    pLength=m;
         } 
         });
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        Registration = new javax.swing.JPanel();
        invalidName = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        eID = new javax.swing.JTextField();
        choosePassword = new javax.swing.JButton();
        invalidEmail = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        SetPassword = new javax.swing.JPanel();
        ChooseImage = new javax.swing.JButton();
        ImageLabel = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();
        Next = new javax.swing.JButton();
        ConfirmPassword = new javax.swing.JPanel();
        ImageLabel1 = new javax.swing.JLabel();
        warning1 = new javax.swing.JLabel();
        Confirm = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        Login = new javax.swing.JPanel();
        ImageLabel2 = new javax.swing.JLabel();
        warning2 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        forgotPassword = new javax.swing.JButton();
        enterOTP = new javax.swing.JPanel();
        otpSent = new javax.swing.JLabel();
        otp = new javax.swing.JTextField();
        submitOTP = new javax.swing.JButton();
        OTPwarning = new javax.swing.JLabel();
        Registration1 = new javax.swing.JPanel();
        invalidName1 = new javax.swing.JLabel();
        userName1 = new javax.swing.JTextField();
        eID1 = new javax.swing.JTextField();
        choosePassword1 = new javax.swing.JButton();
        invalidEmail1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Encrypt = new javax.swing.JPanel();
        encryptButton = new javax.swing.JButton();
        dragdrop = new javax.swing.JLabel();
        help = new javax.swing.JLabel();
        activityLog = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        resetPassword = new javax.swing.JLabel();
        changeEID = new javax.swing.JLabel();
        myFiles = new javax.swing.JLabel();
        decryptButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        notifications = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        MainPanel.setLayout(new java.awt.CardLayout());

        Registration.setPreferredSize(new java.awt.Dimension(530, 372));
        Registration.setLayout(null);

        invalidName.setForeground(new java.awt.Color(254, 254, 254));
        Registration.add(invalidName);
        invalidName.setBounds(98, 120, 259, 30);

        userName.setText("Enter Username");
        userName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userNameFocusLost(evt);
            }
        });
        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });
        Registration.add(userName);
        userName.setBounds(98, 76, 259, 38);

        eID.setText("Enter email ID");
        eID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                eIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                eIDFocusLost(evt);
            }
        });
        Registration.add(eID);
        eID.setBounds(98, 156, 259, 38);

        choosePassword.setText("Choose a Password");
        choosePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosePasswordActionPerformed(evt);
            }
        });
        Registration.add(choosePassword);
        choosePassword.setBounds(98, 242, 259, 29);

        invalidEmail.setForeground(new java.awt.Color(237, 235, 235));
        Registration.add(invalidEmail);
        invalidEmail.setBounds(98, 200, 259, 30);

        jLabel3.setText("jLabel3");
        Registration.add(jLabel3);
        jLabel3.setBounds(0, 0, 510, 350);

        MainPanel.add(Registration, "card7");
        Registration.getAccessibleContext().setAccessibleName("Registration");

        SetPassword.setBackground(new java.awt.Color(254, 254, 254));
        SetPassword.setName("SetPassword"); // NOI18N
        SetPassword.setPreferredSize(new java.awt.Dimension(530, 372));

        ChooseImage.setText("CHOOSE IMAGE");
        ChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseImageActionPerformed(evt);
            }
        });

        note.setText("*Note: Select a minimum of 5 points on the image and remember it as password.");

        Next.setText("NEXT");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SetPasswordLayout = new javax.swing.GroupLayout(SetPassword);
        SetPassword.setLayout(SetPasswordLayout);
        SetPasswordLayout.setHorizontalGroup(
            SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetPasswordLayout.createSequentialGroup()
                .addGroup(SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SetPasswordLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(ChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SetPasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SetPasswordLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Next)
                            .addGroup(SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SetPasswordLayout.setVerticalGroup(
            SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetPasswordLayout.createSequentialGroup()
                .addComponent(ChooseImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(note)
                .addGap(7, 7, 7)
                .addComponent(Next)
                .addContainerGap())
        );

        MainPanel.add(SetPassword, "SetPassword");

        ConfirmPassword.setBackground(new java.awt.Color(254, 254, 254));
        ConfirmPassword.setName("SetPassword"); // NOI18N
        ConfirmPassword.setPreferredSize(new java.awt.Dimension(530, 372));

        warning1.setText("Please check your password!");

        Confirm.setText("CONFIRM");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        Back.setText("BACK");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConfirmPasswordLayout = new javax.swing.GroupLayout(ConfirmPassword);
        ConfirmPassword.setLayout(ConfirmPasswordLayout);
        ConfirmPasswordLayout.setHorizontalGroup(
            ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                .addGroup(ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(warning1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfirmPasswordLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Back)
                .addGap(31, 31, 31)
                .addComponent(Confirm)
                .addGap(20, 20, 20))
        );
        ConfirmPasswordLayout.setVerticalGroup(
            ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(ImageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Confirm)
                    .addComponent(Back))
                .addContainerGap())
        );

        MainPanel.add(ConfirmPassword, "ConfirmPassword");

        Login.setName("SetPassword"); // NOI18N
        Login.setPreferredSize(new java.awt.Dimension(530, 372));

        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        forgotPassword.setText("Forgot Password?");
        forgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(forgotPassword)
                .addGap(18, 18, 18)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(LoginLayout.createSequentialGroup()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(warning2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(ImageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginButton)
                    .addComponent(forgotPassword))
                .addGap(32, 32, 32))
        );

        MainPanel.add(Login, "Login");

        enterOTP.setPreferredSize(new java.awt.Dimension(530, 372));
        enterOTP.setLayout(null);

        otpSent.setText("                 Enter the OTP sent to ypur registered email ID");
        enterOTP.add(otpSent);
        otpSent.setBounds(50, 60, 420, 27);

        otp.setText("OTP");
        otp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                otpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                otpFocusLost(evt);
            }
        });
        otp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otpActionPerformed(evt);
            }
        });
        enterOTP.add(otp);
        otp.setBounds(160, 115, 168, 27);

        submitOTP.setText("Submit");
        submitOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitOTPActionPerformed(evt);
            }
        });
        enterOTP.add(submitOTP);
        submitOTP.setBounds(210, 188, 60, 29);

        OTPwarning.setText("Please check your OTP and try again!");
        enterOTP.add(OTPwarning);
        OTPwarning.setBounds(121, 153, 253, 17);

        MainPanel.add(enterOTP, "enterOTP");
        enterOTP.getAccessibleContext().setAccessibleName("enterOTP");

        Registration1.setPreferredSize(new java.awt.Dimension(530, 372));
        Registration1.setLayout(null);

        invalidName1.setForeground(new java.awt.Color(254, 254, 254));
        Registration1.add(invalidName1);
        invalidName1.setBounds(98, 120, 259, 30);

        userName1.setText("Enter Username");
        userName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userName1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userName1FocusLost(evt);
            }
        });
        userName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userName1ActionPerformed(evt);
            }
        });
        Registration1.add(userName1);
        userName1.setBounds(98, 76, 259, 38);

        eID1.setText("Enter email ID");
        eID1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                eID1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                eID1FocusLost(evt);
            }
        });
        Registration1.add(eID1);
        eID1.setBounds(98, 156, 259, 38);

        choosePassword1.setText("Save ");
        choosePassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosePassword1ActionPerformed(evt);
            }
        });
        Registration1.add(choosePassword1);
        choosePassword1.setBounds(98, 242, 259, 29);

        invalidEmail1.setForeground(new java.awt.Color(237, 235, 235));
        Registration1.add(invalidEmail1);
        invalidEmail1.setBounds(98, 200, 259, 30);

        jLabel4.setText("jLabel3");
        Registration1.add(jLabel4);
        jLabel4.setBounds(0, 0, 510, 350);

        MainPanel.add(Registration1, "Registration1");
        Registration1.getAccessibleContext().setAccessibleName("Registration1");

        Encrypt.setLayout(null);

        encryptButton.setText("Encrypt a file");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });
        Encrypt.add(encryptButton);
        encryptButton.setBounds(239, 30, 102, 29);

        dragdrop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dragdrop.setIcon(new javax.swing.ImageIcon("/home/akku/NetBeansProjects/MiniProjFinal(k)/src/miniprojfinal/imaGES/plus.png")); // NOI18N
        dragdrop.setBorder(new javax.swing.border.MatteBorder(null));
        Encrypt.add(dragdrop);
        dragdrop.setBounds(299, 170, 220, 183);

        help.setIcon(new javax.swing.ImageIcon("/home/akku/NetBeansProjects/MiniProjFinal(k)/src/miniprojfinal/imaGES/icon40x40help - Copy.png")); // NOI18N
        help.setText("Help");
        help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpMouseClicked(evt);
            }
        });
        Encrypt.add(help);
        help.setBounds(20, 170, 170, 50);

        activityLog.setIcon(new javax.swing.ImageIcon("/home/akku/NetBeansProjects/MiniProjFinal(k)/src/miniprojfinal/imaGES/40px-Activity-log.svg.png")); // NOI18N
        activityLog.setText("Activity log");
        activityLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activityLogMouseClicked(evt);
            }
        });
        Encrypt.add(activityLog);
        activityLog.setBounds(20, 228, 162, 40);

        settings.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        settings.setIcon(new javax.swing.ImageIcon("/home/akku/Desktop/download.jpg")); // NOI18N
        settings.setText("Settings");
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
        });
        Encrypt.add(settings);
        settings.setBounds(20, 290, 70, 40);

        resetPassword.setText("Reset Password");
        resetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetPasswordMouseClicked(evt);
            }
        });
        Encrypt.add(resetPassword);
        resetPassword.setBounds(100, 290, 130, 17);

        changeEID.setText("Change emailID");
        changeEID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeEIDMouseClicked(evt);
            }
        });
        Encrypt.add(changeEID);
        changeEID.setBounds(100, 310, 130, 17);

        myFiles.setIcon(new javax.swing.ImageIcon("/home/akku/NetBeansProjects/MiniProjFinal(k)/src/miniprojfinal/imaGES/upload-file-icon-png-28.png")); // NOI18N
        myFiles.setText("My Files");
        myFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myFilesMouseClicked(evt);
            }
        });
        Encrypt.add(myFiles);
        myFiles.setBounds(20, 120, 170, 50);

        decryptButton.setText("Decrypt a file");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });
        Encrypt.add(decryptButton);
        decryptButton.setBounds(347, 30, 104, 29);

        jLabel2.setText("Drop your files here!");
        Encrypt.add(jLabel2);
        jLabel2.setBounds(300, 130, 150, 30);

        notifications.setIcon(new javax.swing.ImageIcon("/home/akku/NetBeansProjects/MiniProjFinal(k)/src/miniprojfinal/imaGES/alert.png")); // NOI18N
        notifications.setText("Notifications");
        notifications.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationsMouseClicked(evt);
            }
        });
        Encrypt.add(notifications);
        notifications.setBounds(20, 70, 180, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon("/home/akku/NetBeansProjects/MiniProjFinal(k)/src/miniprojfinal/imaGES/1-150211232T6-50.jpg")); // NOI18N
        Encrypt.add(jLabel1);
        jLabel1.setBounds(-11, -13, 550, 400);

        MainPanel.add(Encrypt, "Encrypt");
        Encrypt.getAccessibleContext().setAccessibleName("Encrypt");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        MainPanel.add(jPanel1, "card10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.getAccessibleContext().setAccessibleName("MainPanel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseImageActionPerformed
        JFileChooser jfc= new JFileChooser();
         FileFilter imgfilter = new FileNameExtensionFilter(
        "Image files", ImageIO.getReaderFileSuffixes());
         jfc.setFileFilter(imgfilter);
        jfc.showOpenDialog(this);
         f=jfc.getSelectedFile();
         imgpath= f.getAbsolutePath();
        //System.out.println(imgpath);
        LoadImage(ImageLabel);
    }//GEN-LAST:event_ChooseImageActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
       
        /*for(int c=0;c<pLength;c++)
        {
            setP[c]=new CoOrdinate(checkP[c].x,checkP[c].y);
            checkP[c].x=checkP[c].y=0;
        }
        
        pLength=m-1;
        pLength1=pLength;
        System.out.println(pLength);*/
        CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"ConfirmPassword");
        LoadImage(ImageLabel1);
        //Confirm.setVisible(false);
       
    }//GEN-LAST:event_NextActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
            /*for(int check=0;check<=pLength-1;check++)
        {
             //System.out.println(pLength1);
             //System.out.println(pLength);
            if(pLength==pLength1)
            {
                //System.out.println(setP[check].x+","+setP[check].y);
            
            if(checkP[check].x<=setP[check].x+10 && checkP[check].x>=setP[check].x-10 && checkP[check].y<=setP[check].y+10 && checkP[check].y>=setP[check].y-10)
            {
                  System.out.println(checkP[check].x+","+checkP[check].y);
               valid++;
            }
            }
            //System.out.println(valid);
        }
        
        if(valid==pLength)
        {
            //pLength=pLength-1;
           try
              {
                ftemp=new File("temp.txt");
                write("temp.txt",checkP);
                plf= new File("passlength.txt");
                System.out.println("pLength="+pLength);
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter(plf));
                outputWriter.write(""+pLength);
                outputWriter.flush();
                outputWriter.close();
               }
            catch(IOException ioe)
               {
                  System.out.println("IO exception encuntered");
               }
              
             try
             {
              fimgpath=new File("imgname.txt");
              PrintWriter writer = new PrintWriter("imgname.txt", "UTF-8");
              writer.println(f);
              writer.close();
              
             }
             
             catch(Exception e)
             {
                 System.out.println(e);
             }
             CardLayout card=(CardLayout)MainPanel.getLayout();
             card.show(MainPanel,"Encrypt");
        }
        else
        {
            for(int c=0;c<pLength;c++)
            {
              checkP[c]=null;
            }
            warning1.setVisible(true);
            m=0;
            ImageLabel1.repaint();
        }*/
         CardLayout card=(CardLayout)MainPanel.getLayout();
             card.show(MainPanel,"Encrypt");
    }//GEN-LAST:event_ConfirmActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        System.out.println(pLength1);
        System.out.println(pLength);
        for(int check=0;check<pLength1-1;check++)
        {
              if(pLength1==pLength)
            {
                System.out.println("hiu");
            if(checkP[check].x<=loginP[check].x+10 && checkP[check].x>=loginP[check].x-10 && checkP[check].y<=loginP[check].y+10 && checkP[check].y>=loginP[check].y-10)
                valid=1;
        }
        }
        
        if(valid==1)
        { 
         loggedIn=1;
         CardLayout card=(CardLayout)MainPanel.getLayout();
         card.show(MainPanel,"Encrypt");
        }
        else{
                mugvar++;
                if(mugvar<3){
                     m=0;
                     ImageLabel2.repaint();
                }
                else
                {
                            Dimension[] nonStandardResolutions = new Dimension[] {
			WebcamResolution.PAL.getSize(),
			WebcamResolution.HD720.getSize(),
			new Dimension(2000, 1000),
			new Dimension(1000, 500),
		};
		//@formatter:on

		// your camera have to support HD720p to run this code
		Webcam webcam = Webcam.getDefault();
		webcam.setCustomViewSizes(nonStandardResolutions);
		webcam.setViewSize(WebcamResolution.HD720.getSize());
		webcam.open();
                try {
					String name = String.format("test-%d.jpg", System.currentTimeMillis());
					ImageIO.write(webcam.getImage(), "JPG", new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/mugshots/"+name));
                                        File flag= new File("flagfile.txt");
                                        if (!flag.exists()) {
                                            flag.createNewFile();
                                        }
                                        BufferedWriter bw = new BufferedWriter(new FileWriter(flag,true));
                                        bw.write("1"+"\n"+name);
                                        bw.close();
					System.out.format("File %s has been saved\n", name);
                                        System.exit(0);
				} catch (Exception t) {
					t.printStackTrace();
				}
                        m=0;
                       ImageLabel2.repaint();
                        }        
    }                    
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        CardLayout card=(CardLayout)MainPanel.getLayout();
         card.show(MainPanel,"SetPassword");
    }//GEN-LAST:event_BackActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        choosePassword.requestFocus();
        submitOTP.requestFocus();
        if(loggedIn==0)
            loadLogin();
        else
        {
            CardLayout card=(CardLayout)MainPanel.getLayout();
            card.show(MainPanel,"Encrypt");
        }
    }//GEN-LAST:event_formWindowActivated

    private void choosePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosePasswordActionPerformed
        // JPanel password= new JPanel();
        username=userName.getText();
        email=eID.getText();
        if(!validateUserName(username))
        invalidName.setText("Enter valid Username!");
        if(!validateEmail(email))
        invalidEmail.setText("Enter valid email ID!");
        if(validateEmail(email) & validateUserName(username))
        {
             choosePassword.setVisible(true);
             System.out.println(username+", "+email);
             try{
                 File reg= new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/registration.txt");
                BufferedWriter outputWriter = null;
                outputWriter = new BufferedWriter(new FileWriter(reg,true));
                outputWriter.write(email+"\n"+username);
                outputWriter.flush();  
                outputWriter.close();
                }
             catch(Exception e){
                 System.out.println(e);
              }
               
               CardLayout card=(CardLayout)MainPanel.getLayout();
               card.show(MainPanel,"SetPassword");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_choosePasswordActionPerformed

    private void forgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotPasswordActionPerformed
       
        final String Susername = "tempo4991@gmail.com";
	final String Spassword = "P@$$word4991";
        
        try{
           // BufferedReader r = new BufferedReader(new FileReader("registration.txt")).useDelimiter("\\s*,\\s*");
            BufferedReader r = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal(k)/registration.txt"));
            
            //System.out.println("email id="+r.readLine());
            fusername=r.readLine();
            System.out.println("email id="+fusername);
        }
        catch(Exception io)
        {
            System.out.println(io);
        }
          
        Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
             
	Session session = Session.getInstance(props,
	new javax.mail.Authenticator() {
         @Override
	protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Susername, Spassword);
		}
	});

	try {

	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(Susername));
	message.setRecipients(Message.RecipientType.TO,
	InternetAddress.parse(fusername));
        final String OTP = generatePassword();
        OTP1=OTP;
        System.out.println("OTP = " + OTP);
        System.out.println("OTP = " + OTP1);
	message.setSubject("Password Recovery");
	message.setText("Here is your one time password: \n"+ OTP);
        Transport.send(message);
        System.out.println("stooping here");
	//JOptionPane.showMessageDialog(null,"A one time password has been mailed to your registered email ID!" );
        
		} catch (MessagingException e) {
                        System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Failed! Please Check Your Internet Connection" );
		} 
        CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"enterOTP");
    }//GEN-LAST:event_forgotPasswordActionPerformed

    private void submitOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitOTPActionPerformed
       String checkOTP=otp.getText();
        System.out.println(otp.getText());
       if(OTP1.equals(checkOTP))
       {
           System.out.println("hufyuyi");
           CardLayout card=(CardLayout)MainPanel.getLayout();
           card.show(MainPanel,"SetPassword");
       }
       else
           OTPwarning.setVisible(true);
    }//GEN-LAST:event_submitOTPActionPerformed

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
            try {
            JFileChooser fc= new JFileChooser();
            fc.showOpenDialog(this);
            File fe;
            fe=fc.getSelectedFile();
            String efilepath,efilename;
            efilepath= fe.getAbsolutePath();
            miniprojfinal.AESFileEncryption.encrypt(efilepath,"/home/akku/NetBeansProjects/MiniProjFinal(k)/encrypted_files");
            efilename=fe.getName();
            JOptionPane.showMessageDialog(null,efilename+" has been successfully encrypted!" );
            writeToLog(efilename,'e');
            fe.delete();
            
        } catch (Exception ex) {
            //Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("This is an exception in encryptButtonActionPerformed!");
        }
      
       
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void activityLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activityLogMouseClicked
        try {
            File alf= new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/logFile.txt");
            alf.setReadOnly();
            java.awt.Desktop.getDesktop().open(alf);
            // TODO add your handling code here:
        } catch (IOException ex) {
            System.out.println("Activity log file not found");
            //Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_activityLogMouseClicked

    private void myFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myFilesMouseClicked
        // TODO add your handling code here:
        miniprojfinal.SingleRootFileSystemView fsv;
        final File dirToLock = new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/encrypted_files");
        fsv = new SingleRootFileSystemView(dirToLock);
        JFileChooser fc = new JFileChooser(fsv);
        fc.setDialogTitle("My Files");
        fc.showOpenDialog(this);
        fc.setFileView(new FileView() {
        @Override
        public Boolean isTraversable(File f) {
         return dirToLock.equals(f);
        } });
    }//GEN-LAST:event_myFilesMouseClicked

    private void userNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFocusGained
        userName.setText("");
    }//GEN-LAST:event_userNameFocusGained

    private void userNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFocusLost
        if(userName.getText().equals(""))
            userName.setText("Enter Username");
    }//GEN-LAST:event_userNameFocusLost

    private void eIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eIDFocusGained
        eID.setText("");
    }//GEN-LAST:event_eIDFocusGained

    private void eIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eIDFocusLost
        if(eID.getText().equals(""))
            eID.setText("Enter email ID");
    }//GEN-LAST:event_eIDFocusLost

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
         // TODO add your handling code here:
        miniprojfinal.SingleRootFileSystemView fsv;
            final File dirToLock = new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/encrypted_files");
            fsv = new SingleRootFileSystemView(dirToLock);
            JFileChooser fc = new JFileChooser(fsv);
            fc.showOpenDialog(this);
            fc.setFileView(new FileView() {
            @Override
            public Boolean isTraversable(File f) {
                return dirToLock.equals(f);
            } });
            File fe;
            fe=fc.getSelectedFile();
            String der= fe.getAbsolutePath();
            if (JOptionPane.showConfirmDialog(null, "Do you wish to Decrypt "+fe.getName()+" ?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
            JFileChooser fd = new JFileChooser();
            fd.setDialogTitle("Choose Destination Directory");
            fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fd.showSaveDialog(null);
            //System.out.println(f.getCurrentDirectory());
            File direc=fd.getSelectedFile();
            String dir=direc.getAbsolutePath();
            
            miniprojfinal.AESFileDecryption.decrypt(der,dir);
            JOptionPane.showMessageDialog(null,fe.getName()+" has been successfully decrypted!" );
            writeToLog(fe.getName(),'d');
            fe.delete();
        } catch (Exception ex) {
            Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
            } 
    }//GEN-LAST:event_decryptButtonActionPerformed

    private void notificationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsMouseClicked
        // TODO add your handling code here:
        try {
            String l1,l2;
            BufferedReader r = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal(k)/flagfile.txt"));
            l1=r.readLine();
            l2=r.readLine();
            if(Integer.parseInt(l1)==1) {
                File mugf= new File("/home/akku/NetBeansProjects/MiniProjFinal(k)/mugshots/"+l2);
                mugf.setReadOnly();
                java.awt.Desktop.getDesktop().open(mugf);
            }
            PrintWriter clr = new PrintWriter("/home/akku/NetBeansProjects/MiniProjFinal(k)/flagfile.txt");
            clr.print("");
            clr.close();
            // TODO add your handling code here:
        } catch (IOException ex) {
            System.out.println("Activity log file not found");
            //Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_notificationsMouseClicked

    private void otpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otpActionPerformed

    private void otpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_otpFocusGained
        // TODO add your handling code here:
        otp.setText("");
    }//GEN-LAST:event_otpFocusGained

    private void otpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_otpFocusLost
        if(otp.getText().equals(""))
            otp.setText("Enter OTP");
    }//GEN-LAST:event_otpFocusLost

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
        resetPassword.setVisible(true);
        changeEID.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_settingsMouseClicked

    private void resetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetPasswordMouseClicked
        CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"SetPassword");
        resetPassword.setVisible(false);
        changeEID.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_resetPasswordMouseClicked

    private void changeEIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeEIDMouseClicked
            CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"Registration1");
        resetPassword.setVisible(false);
        changeEID.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_changeEIDMouseClicked

    private void userName1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userName1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_userName1FocusGained

    private void userName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userName1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_userName1FocusLost

    private void userName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userName1ActionPerformed

    private void eID1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eID1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_eID1FocusGained

    private void eID1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eID1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_eID1FocusLost

    private void choosePassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosePassword1ActionPerformed
        CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"Encrypt");
        resetPassword.setVisible(false);
        changeEID.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_choosePassword1ActionPerformed

    private void helpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMouseClicked
        CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"jPanel1");
    }//GEN-LAST:event_helpMouseClicked
/**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
            */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new MiniProjFinal().setVisible(true);
                }
            });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton ChooseImage;
    private javax.swing.JButton Confirm;
    private javax.swing.JPanel ConfirmPassword;
    private javax.swing.JPanel Encrypt;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel ImageLabel1;
    private javax.swing.JLabel ImageLabel2;
    private javax.swing.JPanel Login;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton Next;
    private javax.swing.JLabel OTPwarning;
    private javax.swing.JPanel Registration;
    private javax.swing.JPanel Registration1;
    private javax.swing.JPanel SetPassword;
    private javax.swing.JLabel activityLog;
    private javax.swing.JLabel changeEID;
    private javax.swing.JButton choosePassword;
    private javax.swing.JButton choosePassword1;
    private javax.swing.JButton decryptButton;
    private javax.swing.JLabel dragdrop;
    private javax.swing.JTextField eID;
    private javax.swing.JTextField eID1;
    private javax.swing.JButton encryptButton;
    private javax.swing.JPanel enterOTP;
    private javax.swing.JButton forgotPassword;
    private javax.swing.JLabel help;
    private javax.swing.JLabel invalidEmail;
    private javax.swing.JLabel invalidEmail1;
    private javax.swing.JLabel invalidName;
    private javax.swing.JLabel invalidName1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel myFiles;
    private javax.swing.JLabel note;
    private javax.swing.JLabel notifications;
    private javax.swing.JTextField otp;
    private javax.swing.JLabel otpSent;
    private javax.swing.JLabel resetPassword;
    private javax.swing.JLabel settings;
    private javax.swing.JButton submitOTP;
    private javax.swing.JTextField userName;
    private javax.swing.JTextField userName1;
    private javax.swing.JLabel warning;
    private javax.swing.JLabel warning1;
    private javax.swing.JLabel warning2;
    // End of variables declaration//GEN-END:variables
}
