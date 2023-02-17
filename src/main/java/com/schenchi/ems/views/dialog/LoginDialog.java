package com.schenchi.ems.views.dialog;

import java.io.IOException;

import com.schenchi.ems.Main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class LoginDialog extends Dialog<Boolean> {
	
	private GridPane grid;
	
	private TextField usernameField;
	private PasswordField passwordField;
	
	
	public LoginDialog() {
		init();
	}

	private void init() {
		initContent();
		initButtons();
	}

	private void initContent() {
		Image image = null;
		try(var fin = Main.class.getResourceAsStream("/images/user/user.png")){
			image = new Image(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 设置图标和标题
		Stage stage = (Stage) getDialogPane().getScene().getWindow();
		stage.getIcons().add(image);
		setTitle("登录（帐户: admin 密码: admin）");
		setGraphic(new ImageView(image));
		
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		
		int line = 0;
		
		usernameField = new TextField();
		grid.add(new Label("帐户"), 0, line);
		grid.add(usernameField, 1, line);
		line ++;
		
		passwordField = new PasswordField();
		grid.add(new Label("密码"), 0, line);
		grid.add(passwordField, 1, line);
		line ++;
		
		getDialogPane().setContent(grid);
	}
	
	private void initButtons() {
		
		ButtonType loginButton = new ButtonType("登录",ButtonData.YES);
		getDialogPane().getButtonTypes().add(loginButton);
		
		setResultConverter(new Callback<ButtonType, Boolean>() {
			
			private Alert alert;
			
			{
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("登录失败");
				alert.setHeaderText("登录失败！");
				alert.setContentText("登录失败，请检查网络或帐户密码");
			}
			
			@Override
			public Boolean call(ButtonType param) {
				
				if (param == loginButton) {
					String username = usernameField.getText();
					String password = passwordField.getText();
					
					var result = username.equals("admin") && password.equals("admin");
					if (!result) {
						alert.show();
					} else {
						return result;
					}
				}
				
				return null;
			}
		});
		
		// 用于处理关闭事件
		this.setOnCloseRequest(new EventHandler<DialogEvent>() {
			
			@Override
			public void handle(DialogEvent event) {
				
				var result = getResult();
				
				// 阻止关闭事件
				if (result == null) {
					event.consume();
				}
				
			}
		});
	}
}
