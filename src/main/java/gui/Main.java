package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.UdpNetworkAccess;
import settings.Settings;

public class Main extends Application {

    UdpNetworkAccess udpNetworkAccess;
    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/LoginGuiMain.fxml"));
       Parent rootLogin = loaderLogin.load();
       Scene sceneLogin = new Scene(rootLogin,235,218);
       Stage stageLogin = new Stage();
       stageLogin.setTitle("Login");
       stageLogin.setScene(sceneLogin);
       stageLogin.showAndWait();
        System.out.println(Settings.userDetails.getNick());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 734, 573);
        Stage stage = new Stage();
        stage.setTitle("Chat Application");
        stage.setScene(scene);
        stage.show();

        Controller c=loader.<Controller>getController();
        ChatMultiThreadController sss = new ChatMultiThreadController(c);
        udpNetworkAccess = new UdpNetworkAccess(sss);

        c.setUdpNetworkAccess(udpNetworkAccess);

        udpNetworkAccess.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
