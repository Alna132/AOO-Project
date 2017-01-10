package ie.gmit.sw;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 * An AppWindow class that sets out the layout of the pop-up window for the program.
 */

public class AppWindow {
	private JFrame frame;
	private AppSummary appSummary;
	
	public AppWindow(){
		//Create a window for the application
		frame = new JFrame();
		frame.setTitle("B.Sc. in Software Development - GMIT");
		frame.setSize(550, 500);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
        //The file panel will contain the file chooser
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select File to Encode"));
        top.setPreferredSize(new java.awt.Dimension(500, 100));
        top.setMaximumSize(new java.awt.Dimension(500, 100));
        top.setMinimumSize(new java.awt.Dimension(500, 100));
        
        final JTextField txtFileName =  new JTextField(20);
		txtFileName.setPreferredSize(new java.awt.Dimension(100, 30));
		txtFileName.setMaximumSize(new java.awt.Dimension(100, 30));
		txtFileName.setMargin(new java.awt.Insets(2, 2, 2, 2));
		txtFileName.setMinimumSize(new java.awt.Dimension(100, 30));
		
		JButton btnChooseFile = new JButton("Browse...");
		btnChooseFile.setToolTipText("Select File to Encode");
        btnChooseFile.setPreferredSize(new java.awt.Dimension(90, 30));
        btnChooseFile.setMaximumSize(new java.awt.Dimension(90, 30));
        btnChooseFile.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnChooseFile.setMinimumSize(new java.awt.Dimension(90, 30));
		btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
        		JFileChooser fc = new JFileChooser("./");
        		int returnVal = fc.showOpenDialog(frame);
            	if (returnVal == JFileChooser.APPROVE_OPTION) {
                	File file = fc.getSelectedFile().getAbsoluteFile();
                	String name = file.getAbsolutePath(); 
                	txtFileName.setText(name);
                	System.out.println("You selected the following file: " + name);
            	}//- End of if
			}//- End of actionPerformed
        });
		
		JButton btnOther = new JButton("Calculate Stability");
		btnOther.setToolTipText("Calculate Stability of the selected JAR");
		btnOther.setPreferredSize(new java.awt.Dimension(150, 30));
		btnOther.setMaximumSize(new java.awt.Dimension(150, 30));
		btnOther.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnOther.setMinimumSize(new java.awt.Dimension(150, 30));
		btnOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	//-System.out.println("Do Something");
                //- Check if there is something entered into the filepath
            	if(txtFileName.getText().length() > 1){
                    //- Calculate the Metrics of the JAR
                    MetricCalculator metricCalculator = new MetricCalculator(txtFileName.getText());
                    
                    //- Create instance of the AppSummary
                    appSummary =  new AppSummary(frame, true);
                    
                    //- Get handle on summary table model
                    TypeSummaryTableModel tableModel = appSummary.getTableModel();
                    //- Add metric data into table model.
                    tableModel.setTableData(metricCalculator.getMetricData());
                    //- Make the dialog visible
                    appSummary.setVisible(true);
                } else {
                    System.out.println("No jar selected");
                }//- End of if/else
			}//- End of actionPerformed
        });
		
        top.add(txtFileName);
        top.add(btnChooseFile);
        top.add(btnOther);
        frame.getContentPane().add(top); //Add the panel to the window
        
        
        //A separate panel for the programme output
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEADING));
        mid.setBorder(new BevelBorder(BevelBorder.RAISED));
        mid.setPreferredSize(new java.awt.Dimension(500, 300));
        mid.setMaximumSize(new java.awt.Dimension(500, 300));
        mid.setMinimumSize(new java.awt.Dimension(500, 300));
        
        //- White board with coloured dots. Not needed right now.
        CustomControl cc = new CustomControl(new java.awt.Dimension(500, 300));
        cc.setBackground(Color.WHITE);
        cc.setPreferredSize(new java.awt.Dimension(300, 300));
        cc.setMaximumSize(new java.awt.Dimension(300, 300));
        cc.setMinimumSize(new java.awt.Dimension(300, 300));
        mid.add(cc);
		frame.getContentPane().add(mid);
		
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new java.awt.Dimension(500, 50));
        bottom.setMaximumSize(new java.awt.Dimension(500, 50));
        bottom.setMinimumSize(new java.awt.Dimension(500, 50));
        
        JButton btnDialog = new JButton("Show Dialog"); //Create Quit button
        btnDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	AppSummary as =  new AppSummary(frame, true);
            	as.show();
			}//- End of actionPerformed
        });
        
        JButton btnQuit = new JButton("Quit"); //Create Quit button
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
			}//- End of actionPerformed
        });
        bottom.add(btnDialog);
        bottom.add(btnQuit);

        frame.getContentPane().add(bottom);       
		frame.setVisible(true);
	}//- End of AppWindow()
	
	public static void main(String[] args) {
		new AppWindow();
	}//- End of main
}//- End of App Window
