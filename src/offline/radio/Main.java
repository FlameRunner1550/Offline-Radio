import java.awt.BorderLayout;
import java.io.File;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Main {
    public static void main(String[] args) {
        System.out.println("Offline Radio started.");
        // Create instances of Mp3Player and OfflineRadio
        //Mp3Player mp3Player = new Mp3Player();
        //OfflineRadio offlineRadio = new OfflineRadio();

        Mp3Player mp3Player = new Mp3Player();

        //Window Basics
        
        JFrame frame = new JFrame("Offline Radio");
        JPanel buttonPanelControl = new JPanel();
        JPanel buttonPanelMusicLoad = new JPanel();
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1200);
        frame.setVisible(true);
        frame.setTitle("Offline Radio");
        
        //Table to display what is scanned in!

        JTable musicTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(musicTable);
        


        //Basic Buttons

        //Play Button
        JButton playButton = new JButton("Play");

        frame.add(playButton);
        playButton.setBounds(250,100,100,50);
        playButton.setVisible(true);

        playButton.addActionListener(e -> {

            mp3Player.pingList();

        });

        //Pause Button
        JButton pauseButton = new JButton("Pause");
        
        frame.add(pauseButton);
        pauseButton.setBounds(400,100,100,50);
        pauseButton.setVisible(true);

        pauseButton.addActionListener(e -> {

            System.out.println("Pause button clicked!");

        });

        //Last Track Button
        JButton lastTrackButton = new JButton("Last Track");

        frame.add(lastTrackButton);
        lastTrackButton.setBounds(100,100,100,50);
        lastTrackButton.setVisible(true);

        lastTrackButton.addActionListener(e -> {

            System.out.println("Last Track button clicked!");

        });

        //Skip Button

        JButton skipButton = new JButton("Skip");
        frame.add(skipButton);
        skipButton.setBounds(550,100,100,50);
        skipButton.setVisible(true);

        skipButton.addActionListener(e -> {

            System.out.println("Skip button clicked!");

        });


        //Loaded Music Label
        JLabel loadedMusicLabel = new JLabel("Loaded Music: None");
        loadedMusicLabel.setBounds(700, 200, 300, 50);
        loadedMusicLabel.setVisible(true);
        
        //Loading in the music files from the music directory

        JButton musicFolderButton = new JButton("Select Music Folder");
        musicFolderButton.setBounds(700, 100, 50, 50);
        musicFolderButton.setVisible(true);
        frame.add(musicFolderButton);
        musicFolderButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                System.out.println("Selected music folder: " + selectedDirectory.getAbsolutePath());
                // Here you can add logic to load music files from the selected directory
                loadedMusicLabel.setText("Loaded Music: " + selectedDirectory.getName());
                
                File musicFolder = new File(selectedDirectory.getAbsolutePath());

                musicFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3") || name.toLowerCase().endsWith(".wav") || name.toLowerCase().endsWith(".flac"));
                
                DefaultTableModel model = (DefaultTableModel) musicTable.getModel();
                
                for (File file : musicFolder.listFiles()) {

                    System.out.println("Found music file: " + file.getName());
                    
                    
    


                    LinkedList<String> currentList = new LinkedList<>();
                    if (model.getColumnCount() == 0) {
                        model.addColumn("Filename");
                        model.addColumn("Size");
                        model.addColumn("Last Modified");
                        
                    }

                    

                    model.addRow(new Object[] { file.getName(), (file.length() / 1024) + " KB", new java.util.Date(file.lastModified()).toString() } );
                    
                    currentList.add(file.getName());
                    mp3Player.setSongList(currentList);
                    System.out.println("Current Song List: " + mp3Player.getSongList());

                    

                }
                musicTable.setEnabled(false);
            }
        });

        

      



        // Adding buttons to panel and panel to frame

        //BOTTOM SIDE
        buttonPanelControl.add(lastTrackButton);
        buttonPanelControl.add(playButton);
        buttonPanelControl.add(pauseButton);
        buttonPanelControl.add(skipButton);
        frame.add(buttonPanelControl, BorderLayout.SOUTH);
        frame.setVisible(true);

        //RIGHT SIDE
        buttonPanelMusicLoad.add(musicFolderButton);
        buttonPanelMusicLoad.add(loadedMusicLabel);
        frame.add(buttonPanelMusicLoad, BorderLayout.EAST);
        frame.setVisible(true);


        //CENTER - TABLE
        frame.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVisible(true);

    }
}
