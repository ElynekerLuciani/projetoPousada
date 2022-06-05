/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Elyneker
 */
public class BackupDatabase {
    private static final String USER = "root";
    private static final String PASS = "admin";
    private static final String DATABASE = "pousada";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    private static final String NAME = dtf.format(LocalDateTime.now())+".sql";
    
    public static boolean backup(){
        try {
            //Properties properties = System.getProperties();
            String operationalSystem = System.getProperty("os.name");
            String[] os = operationalSystem.split(" ");
       
        switch (os[0]) {
            case "Windows":
                return backupWindows();
            case "Linux":
                break;
            default:
                return false;
        }
           
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;     
    }
    
    private static boolean backupWindows(){
        try {
            File diretorio = new File("C:\\Backup");
            
            if (!diretorio.exists()){
                diretorio.mkdirs();
            }
            
            String executeCmd = "mysqldump -u" + USER + " -p" + PASS + " " + DATABASE + " > " + NAME;
               
            ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\Backup\" && " + executeCmd);
                
            Process p = builder.start();
            int complete = p.waitFor();
            
            if(complete == 0)
                return true;
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        return false;
    }
}
