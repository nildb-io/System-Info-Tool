package com.example.actualjavafxproject.beta_project;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.util.Duration;

import java.util.*;

public class sysinfotoolexe extends Application {
    private List<Label> sysinfolabel;
    private ProgressBar diskBar;
    private Label diskLabel;
    private Timeline timeline;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: #0b0f14;");
        //LEFT TILING FOR LOGO
        HBox centralbox = new HBox(40);
        Label logo = new Label();
        logo.setStyle("""
                -fx-font-family: Monospaced;
                -fx-font-size: 14;
                -fx-text-fill: #f5a623;
                """);
        StackPane leftslide = new StackPane();
        String os = System.getProperty("os.name").toLowerCase();
        if (Objects.equals(System.getProperty("os.name"), "Windows 11")) {
            logo.setText("""
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    | | | | | | | |  | | | | | | | |
                    
                    """);
        } else if (Objects.equals(System.getProperty("os.name"), "Windows 10")) {
            logo.setText("""
                                              .~oodMMMM
                                      .oodMMMMMMMMMMMMM
                          ..oodMMM  MMMMMMMMMMMMMMMMMMM
                    oodMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMM
                    `^^^^^^MMMMMMM  MMMMMMMMMMMMMMMMMMM
                          ````^^^^  ^^MMMMMMMMMMMMMMMMM
                                         ````^^^^^^MMMM
                    
                    """);
        } else if (Objects.equals(System.getProperty("os.name"), "Windows 7")) {
            logo.setText("""
                              ,=::!!t3Z3z.,
                             t:::::tt333EE3`.
                            t::.:zt t333EEL/  Ee.,         ..,
                           tt::::tt333EE7// /;EEEEEEtttttt33#
                          :Et::.:zt333EEQ. /$EEEEEEttttt33QL`
                         /it::::tt333EEF/ /@EEEEEEttttt33F//
                        //;3=^````*4E#EV /EEEEEEtttt33@.##/
                       ,. ..:==.,~  . ` ,@EEEEEEttttz33QF/
                       ###;::::::zt33)   ."4EEEtttji3P*//
                      /:t:::::::tt33./ /Z3z..  `` ,..g.`
                     /##i:::::::zt33F AEEEtttt:::::ztF/
                    /##;:::::::t33V/ ;EEEttttt:::::t3/
                   ///E::::::::zt33 /EEEtttt:::::z3F/
                   ###{3=^````*4E3) /;EEEtttt:::::tZ`
                                 ` :EEEtttt:::::z7/
                                      "VEzjt;;;z>*
                    """);
        }
        leftslide.getChildren().addAll(logo);
        leftslide.setAlignment(Pos.BASELINE_LEFT);
//        root.setLeft(leftslide);

        //Right Tiling
        VBox rightslide = new VBox(5);
        //For Disk UI
        diskLabel = new Label("Disk Usage:          ");
        diskLabel.setStyle("-fx-font-family: Monospaced; -fx-font-size: 16; -fx-text-fill: #e6edf3;");
        diskBar = new ProgressBar(0);
        diskBar.setPrefWidth(260);
        diskBar.setStyle("-fx-accent: #f5a623;");
        VBox diskBox = new VBox(6, diskLabel, diskBar);
        rightslide.getChildren().add(diskBox);
        //FOR STORING AND PROCESSING LABELS
        sysinfolabel = new ArrayList<>();
        List<String> sysinfo = Arrays.asList("OS:  ", "Arch:  ", "Java:  ", "User:  ", "CPU cores:  ", "JVM RAM: ", "System RAM:  " ,"Disk:  ", "Uptime:  "); //storing names in the form of strings
        for (String var : sysinfo) {
            Label label = new Label(var); //passing var parameters
            label.setStyle("-fx-font-family: Monospace; -fx-font-size: 16;-fx-text-fill: #e6edf3;");
            sysinfolabel.add(label); //adding label in sysinfo taken as label list
            rightslide.getChildren().add(label);
        }
        updateAll();
        revealLines(sysinfolabel);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> updateAll()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        centralbox.getChildren().addAll(leftslide, rightslide);
        HBox.setHgrow(rightslide, Priority.ALWAYS); //to maitain rightside spacing
        rightslide.setFillWidth(true);
        root.setCenter(centralbox);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SysInfoTool -Beta Release v1.0");
        primaryStage.show();
        //Typewriter reveal system for whole tiling window

    }
    //For System Memory (OS RAM)
    private String getSystemRamUsage(){
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long total = osBean.getTotalMemorySize();
        long free = osBean.getFreeMemorySize();
        long used = total - free;
        return String.format("%s / %s",convert(used),convert(total));
    }

    //For JVM Memory
    private static long getJvmUsedBytes() {   //TO GET USED MEMORY
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

    private static long getJvmMaxMemory() {    //TO GET MAX MEMORY
        return Runtime.getRuntime().maxMemory();
    }

    private static String convert(long bytes) {
        double gb = bytes / (1024.0 * 1024.0 * 1024.0);
        if (gb >= 1)
            return String.format(Locale.ROOT, "%.1fGB", gb);
        double mb = bytes / (1024.0 * 1024.0);
        if (mb >= 1)
            return String.format(Locale.ROOT, "%.1fMB", mb);
        double kb = bytes / (1024.0);
        if (kb >= 1)
            return String.format(Locale.ROOT, "%.1fKB", kb);
        return bytes + "B";
    }
    long used = getJvmUsedBytes();
    long max = getJvmMaxMemory();
    //For Storage management PART 1:
    private static long[] getDiskUsedAndTotal() {
        try {
            Path home = Path.of(System.getProperty("user.home"));
            FileStore store = Files.getFileStore(home);

            long total = store.getTotalSpace();
            long free = store.getUsableSpace();
            long used = total - free;
            return new long[]{used, total};
        } catch (Exception e) {
            return null;
        }
    }
    //JVM Uptime
    private static String getJvmUpTime(){
        long ms = ManagementFactory.getRuntimeMXBean().getUptime();
        long totalseconds = ms/1000;
        long hours = totalseconds / 3600;
        long mins = (totalseconds % 3600)/60;
        long secs = totalseconds%60;
        return String.format("%02d : %02d : %02d " , hours,mins,secs);
    }
    //Typewriter effect for whole tiling window
    private void revealLines(List<Label>labels){
        Timeline reveal = new Timeline();
        double delay = 700.0;
        for(Label lbl : labels) {
            lbl.setOpacity(0);
            KeyFrame kf = new KeyFrame(Duration.millis(delay), event -> lbl.setOpacity(1));
            reveal.getKeyFrames().add(kf);
            delay+=200;
        }
        reveal.play();
    }
    private void updateAll(){
        sysinfolabel.get(0).setText("OS:               " + System.getProperty("os.name"));
        //For Processor arch
        String Arch = System.getProperty("os.arch");
        if("amd64".equalsIgnoreCase(Arch)|| "x86_64".equalsIgnoreCase(Arch)){
            Arch = "x86_64(64-bit)";
            sysinfolabel.get(1).setText("Arch:             " + Arch);
        } else {
            sysinfolabel.get(1).setText("OS:               " + System.getProperty("os.arch"));
        }
        sysinfolabel.get(2).setText("Java version:     " + System.getProperty("java.version"));
        sysinfolabel.get(3).setText("User:             " + System.getProperty("user.name"));
        sysinfolabel.get(4).setText("CPU cores:        " + String.format( "%s  (logical) ", Runtime.getRuntime().availableProcessors()));
        sysinfolabel.get(5).setText("JVM RAM:          " + String.format("%s / %s" ,convert(used),convert(max)));
        sysinfolabel.get(6).setText("System RAM:       " + getSystemRamUsage());
        //For Storage management PART 2:
        long[] disk = getDiskUsedAndTotal();
        if(disk !=null && disk[1]>0)
        {
            long used = disk[0];
            long total = disk[1];
            sysinfolabel.get(7).setText("Local Disk(C:):   "    + String.format("%s / %s", convert(used), convert(total)));

            double progress = (double) used / (double) total;
            diskBar.setProgress(progress);
        }else{
            sysinfolabel.get(7).setText("Disk:        " + "    -");
            diskBar.setProgress(0);
        }
        sysinfolabel.get(8).setText("Uptime:           " + getJvmUpTime());
    }
}
