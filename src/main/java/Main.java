import de.sokahr.ttt.ConfigurationReader;
import de.sokahr.ttt.PropertyConfigurationReader;
import de.sokahr.ttt.TicTacToeIOSystem;
import de.sokahr.ttt.TicTacToeTwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = getProperties();
        ConfigurationReader configurationReader = new PropertyConfigurationReader(properties);
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(ticTacToeIOSystem);
        while (true){
            ticTacToeTwo.configureGame( configurationReader.read(ticTacToeIOSystem) );
            ticTacToeTwo.play();
            ticTacToeIOSystem.showInfoMessage("Play another round? [Y/N]");
            try {
                String input = ticTacToeIOSystem.getInput();
                if(input.toUpperCase().contains("N")) {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        InputStream inputStream;

        try {
            //get a config file from the installation folder
            inputStream = new FileInputStream("../config.properties");
        } catch (FileNotFoundException e) {
            //if no configuration file was found use the internal default
            System.out.println("config.properties file not found use defaults.");
            inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
        }

        try {
            properties.load(inputStream);
        } catch (IOException e1) {
            System.out.println("could not load properties");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return properties;
    }
}
