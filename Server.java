

/*
*Author:Pravalika Gunti
*Project: Multi Client Chat System Using GUI
*This server program using TCP/IP.
*This server program handles multiple clients
*
*/
import java.net.*;
import java.io.*;
import java.util.*;


public class Server extends javax.swing.JFrame{
	private final static int  DEST_PORT = 5901;
        BufferedReader in;
        PrintWriter out ;
        ArrayList client=new ArrayList();
        ArrayList<Socket> clientS=new ArrayList<Socket>();
	private Socket clientSocket;
        String str;
        ServerSocket svrSocket;
        boolean connection=false;

    /*
     *This is default constructor of the class here all components were initialized    
     */
    public Server() {
        initComponents();
        Start.setEnabled(true);      
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Start.setText("START");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(Start)
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(Start)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /*
    *when user clicks start button, it starts the server
    */
    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed

        try{        
	 svrSocket = new ServerSocket(DEST_PORT);
         System.out.println("Server running...");
         while(true)
		{   
                    jPanel1.requestFocusInWindow();
                                clientSocket=svrSocket.accept();
                                if(!clientS.contains(clientSocket)){
                                clientS.add(clientSocket);
                                connection=true;
                                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    				str = in.readLine();
                                client.add(str);
                                for(int i=0;i<client.size();i++){
                                PrintWriter ou=new PrintWriter(clientS.get(i).getOutputStream(), true);
                                ou.println("[@"+str+"@] "+"has joined");
                                }
                                String message="";
                                        for(int j=0;j<clientS.size();j++){
                                        message=message+client.get(j)+" ";
                                        }
                                for(int i=0;i<client.size();i++){
                                PrintWriter ou=new PrintWriter(clientS.get(i).getOutputStream(), true);
                                ou.println(message);    
                                }
                    }

                                ThreadRuns  runs=new ThreadRuns(clientSocket);
                                Thread thread= new Thread(runs);
                                thread.start();

                        		}
     }
		catch (IOException e){
                    e.printStackTrace();
		
		}
    }//GEN-LAST:event_StartActionPerformed

    /*
    *This is the thread class 
    */
    public class ThreadRuns implements Runnable{
        Socket clientSocket;
        
        public ThreadRuns(Socket s){
            clientSocket=s;
        }
        /*
        *Here I splitted message on 3 basis
        *If message is DISCONNECTED then it removes client socket and client name from array list
        *If normal message it sends to multiple clients
        *Sending message to all clients about active users
        */
        @Override
        public void run() {
           try{
            
                            
                            while(clientS.contains(clientSocket))
				{  
                                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String message = in.readLine();
                                        String[] sp=message.split(" "); 
                                        if(sp[0].equals("Disconnected")&& client.contains(sp[2])){
                                            client.remove(sp[2]);
                                            clientS.remove(clientSocket);
                                        for(int i=0;i<clientS.size();i++){
                                        PrintWriter ou=new PrintWriter(clientS.get(i).getOutputStream(), true);
                                        ou.println("[@****@] "+message);
                                        }
                                        message="";
                                        for(int j=0;j<clientS.size();j++){
                                        message=message+client.get(j)+" ";
                                        }
                                        for(int i=0;i<client.size();i++){
                                        PrintWriter ou=new PrintWriter(clientS.get(i).getOutputStream(), true);
                                        ou.println(message);    
                                        }
                                        }
                                        else{
                                        for(int i=0;i<clientS.size();i++){
                                        PrintWriter ou=new PrintWriter(clientS.get(i).getOutputStream(), true);
                                        ou.println(message);
                                        }
                                        message="";
                                        for(int j=0;j<clientS.size();j++){
                                        message=message+client.get(j)+" ";
                                        }
                                        for(int i=0;i<clientS.size();i++){
                                        PrintWriter ou=new PrintWriter(clientS.get(i).getOutputStream(), true);
                                        ou.println(message);
                                        }
                                }
    }
           }
        catch(Exception e){
         e.printStackTrace();
        }
        }
}
  



   
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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Server().setVisible(true);
            //System.out.print("mmaaa");
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Start;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
