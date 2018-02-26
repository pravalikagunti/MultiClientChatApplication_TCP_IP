

/*
*Author:Pravalika Gunti
*Project: Multi Client Chat System Using GUI
*This client interface implemented using TCP/IP.
*In client interface, in order to connect to the server, client should enter username to display his identity in group chat 
*and also client should enter port number on which server is always active. 
*Client can view all the online users in a separate frame. 
*Any client can disconnect from the group chat by using disconnect button.
*/
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
public class Client extends javax.swing.JFrame{


    String UserField;
    Boolean connection=false;
    private final static String SERVER_NAME = "localhost";
    private static int port1;
    List<String> userList = new ArrayList<String>();
    PrintWriter out;
    BufferedReader in;
    private Socket clientSocket; 
    int[] fontss={Font.BOLD,Font.ITALIC,Font.PLAIN};
    int fnt;
    String i1;
    int i2;
    Font bigFont;
   
    /*This is default constructor of the class where all the components were initialized*/  
    
    public Client() {
       initComponents();
       fnt=fontss[2];
       String[] s=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
       for(int i=0;i<s.length;i++){
       Script.addItem(s[i]);
       }
    }
    
    /*This is parameterized constructor*/
    public Client(Socket clientSocket){
        this.clientSocket=clientSocket;               
    }
    
    /*
    *This is Thread class implementation. Here message is splitted into different formats. 
    *So that it is easy to identify that disconnected message, normal message and client name
    */ 
    public class chatThread implements Runnable{

        @Override
        public void run() {
            
        try{
    
                            while(clientSocket.isConnected())
				{  
                                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String message = in.readLine();
					String[] s=message.split("\\[@|\\@] ");
                                        
                                        if(s.length==1){
                                            C_user.setText("");
                                           String[] s1=message.split(" ");
                                           for(int i=0;i<s1.length;i++){
                                              C_user.append(s1[i]+"\n"); 
                                           }
                                        
                                        }
                                        else{ 
                                            String[] s1=message.split("@/./@");
                                            
                                            if(s1.length>1){
                                            String s234[]=s1[1].split("\\[|\\=|\\,|\\]");                                            
                                            int i5=Integer.valueOf(s234[8]);
                                            String s6= s234[6].toUpperCase();
                                            if(s6.equals("BOLD")){
                                            fnt=1;
                                            }
                                            else if(s6.equals("ITALIC")){
                                            fnt=2;
                                            }
                                            else{
                                            fnt=0;
                                            }
                                            bigFont   = new Font(s234[4],fnt, i5);
                                            All_text_old.setFont(bigFont);
                                            All_text_old.append(s1[0]+"\n");
                                            }                                            
                                            else{
                                         All_text_old.append(message+"\n");
                                            }
                                        }
                            } 
        }
    catch(Exception e){
    JOptionPane.showMessageDialog(null,"Server has stopped");
    User_field.setText("");
    port_num.setText("");
    User_field.setEditable(true);
    port_num.setEditable(true);
    connect.setEnabled(true);
    disconnect.setEnabled(false);
    C_user.setEditable(false);
    bold.setEnabled(false);
    itallic.setEnabled(false);
    plain.setEnabled(false);
    send.setEnabled(false);
    All_text_old.setText("");
    C_user.setText("");
    Script.setEnabled(false); 
    fontSize.setEnabled(false);
        }
        }
    }
    


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        U_name = new javax.swing.JLabel();
        connect = new javax.swing.JButton();
        disconnect = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        C_user = new javax.swing.JTextArea();
        ConnectedUsers = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        chat_area = new javax.swing.JTextArea();
        Script = new javax.swing.JComboBox<>();
        bold = new javax.swing.JButton();
        itallic = new javax.swing.JButton();
        plain = new javax.swing.JButton();
        send = new javax.swing.JButton();
        User_field = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        All_text_old = new javax.swing.JTextArea();
        port_num = new javax.swing.JTextField();
        fontSize = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        U_name.setText("USERNAME");

        connect.setText("CONNECT");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        disconnect.setText("DISCONNECT");
        disconnect.setEnabled(false);
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });

        C_user.setEditable(false);
        C_user.setColumns(20);
        C_user.setRows(5);
        jScrollPane2.setViewportView(C_user);

        ConnectedUsers.setText("Connected Users");

        chat_area.setColumns(20);
        chat_area.setRows(5);
        jScrollPane3.setViewportView(chat_area);

        Script.setEnabled(false);
        Script.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScriptActionPerformed(evt);
            }
        });

        bold.setText("BOLD");
        bold.setEnabled(false);
        bold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldActionPerformed(evt);
            }
        });

        itallic.setText("ITALIC");
        itallic.setEnabled(false);
        itallic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itallicActionPerformed(evt);
            }
        });

        plain.setText("PLAIN");
        plain.setEnabled(false);
        plain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plainActionPerformed(evt);
            }
        });

        send.setText("Send");
        send.setEnabled(false);
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        User_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                User_fieldActionPerformed(evt);
            }
        });

        All_text_old.setEditable(false);
        All_text_old.setColumns(20);
        All_text_old.setRows(5);
        jScrollPane1.setViewportView(All_text_old);

        fontSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "12", "14", "16", "18", "20", "22", "24", "26", "28" }));
        fontSize.setEnabled(false);
        fontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontSizeActionPerformed(evt);
            }
        });

        jLabel1.setText("PORT NUMBER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(U_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(User_field, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(port_num)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnect)
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Script, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bold, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(itallic, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(plain, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ConnectedUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(U_name)
                    .addComponent(connect)
                    .addComponent(User_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disconnect)
                    .addComponent(port_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConnectedUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Script, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(itallic)
                                    .addComponent(bold)
                                    .addComponent(plain)))
                            .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    *In order to connect to client, the client enter username and port number
    *Without giving correct input client cannot connect to the server
    *
    */
    
    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
   if(connection==false){
   User_field.setEditable(true); 
   port_num.setEditable(true);
   UserField = User_field.getText();
   String p=port_num.getText();
 
   if((UserField.equals(""))||p.equals("")){
   JOptionPane.showMessageDialog(null,"Oops!Please enter the user name or port number");
   }
   if(!UserField.equals("")&&(!p.equals(""))){       
      try{
    User_field.setEditable(false);
    port1=Integer.parseInt(port_num.getText());       
    InetAddress dstIP = InetAddress.getByName(SERVER_NAME);
    System.out.println(dstIP+" . \n"+port1);
    clientSocket = new Socket(dstIP,port1);
    userList.add(UserField);   
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    connection =true;
    connect.setEnabled(false);    
    port_num.setEditable(false);
    disconnect.setEnabled(true);
    chat_area.setEnabled(true);
    chat_area.requestFocusInWindow();
    bold.setEnabled(true);
    itallic.setEnabled(true);
    plain.setEnabled(true);
    send.setEnabled(true);
    Script.setEnabled(true);
    fontSize.setEnabled(true);
    out.println(UserField+"");
    C_user.setText("");
    All_text_old.setText("");
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
    chatThread run=new chatThread();
    Thread thread= new Thread(run);
    thread.start();
}
catch(IOException e){
    JOptionPane.showMessageDialog(null,"You didn't connect to the groupchat.Please retry");
    User_field.setText("");
    port_num.setText("");
    User_field.setEditable(true);
    port_num.setEditable(true);
    connect.setEnabled(true);
    disconnect.setEnabled(false);
    C_user.setEditable(false);
    bold.setEnabled(false);
    itallic.setEnabled(false);
    plain.setEnabled(false);
    send.setEnabled(false);
    Script.setEnabled(false); 
    fontSize.setEnabled(false);
    }
   }
  }
    }//GEN-LAST:event_connectActionPerformed

    /*
    *if user press disconnect button
    *user gets a confirmation dialog box ......to disconnect press yes or else no
    *based on users input the action is taken place
    */
    
    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
        int click=JOptionPane.showConfirmDialog(this,"Do you want to disconnect from group chat","Confirm",JOptionPane.YES_NO_OPTION);
        if(click==JOptionPane.YES_OPTION){
        String disconnectMSG = ("Disconnected by "+User_field.getText());
        connection=false;            
        try{
            All_text_old.append("\n"+User_field.getText()+" left the group");
            out.println(disconnectMSG); 
            out.flush();             
        try {               
               Thread.sleep(8000);                 
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
            JOptionPane.showMessageDialog(null,"You left the group chat");
        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Server did not recieve disconnected message");
        }
        
        
        
    User_field.setText("");
    port_num.setText("");
    User_field.setEditable(true);
    port_num.setEditable(true);
    connect.setEnabled(true);
    disconnect.setEnabled(false);
    C_user.setEditable(false);
    bold.setEnabled(false);
    itallic.setEnabled(false);
    plain.setEnabled(false);
    send.setEnabled(false);
    Script.setEnabled(false);
    chat_area.setEnabled(false);
    fontSize.setEnabled(false);
    All_text_old.setText("");
    C_user.setText("");
    chat_area.setText("");
        }
        else{
            chat_area.requestFocusInWindow();
        }
    }//GEN-LAST:event_disconnectActionPerformed

    
    /*
    *When send button is pressed,  
    *It first checks whether message is entered in text area or not
    *If the message is entered then it is send to server
    *If the message is not entered, it popup dialog box and focussed to text area
    */
    
    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        if(chat_area.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please enter text message");
            chat_area.requestFocusInWindow();
        }
        if(!chat_area.getText().equals("")){
            try {
                bigFont   = new Font((String) Script.getSelectedItem(),fnt , Integer.valueOf((String)fontSize.getSelectedItem()));
                chat_area.setFont(bigFont);
                String str=chat_area.getText();
                str=str+"@/./@"+bigFont;               
               out = new PrintWriter(clientSocket.getOutputStream(), true); 
               out.println("[@"+UserField + "@] " + str);
               out.flush(); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"message was not sent");
                chat_area.setText(""); 
                chat_area.requestFocusInWindow();
            }
            chat_area.setText("");
            chat_area.requestFocusInWindow();
         
        }       
    }//GEN-LAST:event_sendActionPerformed

    private void User_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_User_fieldActionPerformed
       
        


    }//GEN-LAST:event_User_fieldActionPerformed

    
    /*
    *This method is used to change the script type
    */

    private void ScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScriptActionPerformed
        bigFont   = new Font((String) Script.getSelectedItem(),fnt , Integer.valueOf((String)fontSize.getSelectedItem()));
         chat_area.setFont(bigFont);

    }//GEN-LAST:event_ScriptActionPerformed

    /*
    *This method is used to change message to itallic type
    */

    
    private void itallicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itallicActionPerformed

    fnt=fontss[1];
    bigFont   = new Font((String) Script.getSelectedItem(),fnt , Integer.valueOf((String)fontSize.getSelectedItem()));
         chat_area.setFont(bigFont);
    }//GEN-LAST:event_itallicActionPerformed

    /*
    *This method is used to change message to bold type
    */
    
    private void boldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldActionPerformed
        fnt=fontss[0];
    bigFont   = new Font((String) Script.getSelectedItem(),fnt , Integer.valueOf((String)fontSize.getSelectedItem()));
         chat_area.setFont(bigFont);

    }//GEN-LAST:event_boldActionPerformed
    
    /*
    *This method is used to change message to font type
    */
    
    private void fontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontSizeActionPerformed
    
    bigFont   = new Font((String) Script.getSelectedItem(),fnt , Integer.valueOf((String)fontSize.getSelectedItem()));
         chat_area.setFont(bigFont);
    }//GEN-LAST:event_fontSizeActionPerformed
    
    /*
    *This method is used to change message to plain type
    */
    
    private void plainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plainActionPerformed
        fnt=fontss[2];
    bigFont   = new Font((String) Script.getSelectedItem(),fnt , Integer.valueOf((String)fontSize.getSelectedItem()));
         chat_area.setFont(bigFont);

    }//GEN-LAST:event_plainActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Client().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea All_text_old;
    private javax.swing.JTextArea C_user;
    private javax.swing.JLabel ConnectedUsers;
    private javax.swing.JComboBox<String> Script;
    private javax.swing.JLabel U_name;
    private javax.swing.JTextField User_field;
    private javax.swing.JButton bold;
    private javax.swing.JTextArea chat_area;
    private javax.swing.JButton connect;
    private javax.swing.JButton disconnect;
    private javax.swing.JComboBox<String> fontSize;
    private javax.swing.JButton itallic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton plain;
    private javax.swing.JTextField port_num;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
    
    }

