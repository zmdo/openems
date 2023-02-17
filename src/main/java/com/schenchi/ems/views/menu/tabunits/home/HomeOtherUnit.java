package com.schenchi.ems.views.menu.tabunits.home;

import java.io.IOException;

import com.schenchi.ems.Main;
import com.schenchi.ems.platform.menu.IMenuFunctionalUnit;
import com.schenchi.ems.platform.menu.MenuFunctionalUnit;
import com.tricolorfire.labfx.control.LabButton;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

@MenuFunctionalUnit
public class HomeOtherUnit implements IMenuFunctionalUnit {
	
	private HBox content;
	
	private LabButton otherAButton;
	private LabButton otherBButton;
	
	public HomeOtherUnit() {
		init();
	}
	
	private void init() {
		initContent();
		initButtons();
	}
	
	private void initContent() {
		content = new HBox();
	}
	
	private void initButtons() {
		initOtherBButton();
		initOtherAButton();
	}

	private void initOtherAButton() {
		Image image = null;
		try(var fin = Main.class.getResourceAsStream("/images/equipment/delete.png")) {
			image = new Image(fin);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
		otherAButton = new LabButton(image,"B\n按钮");
		content.getChildren().add(otherAButton);
	}

	private void initOtherBButton() {
		Image image = null;
		try(var fin = Main.class.getResourceAsStream("/images/equipment/add.png")) {
			image = new Image(fin);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
		otherBButton = new LabButton(image,"A\n按钮");
		content.getChildren().add(otherBButton);
	}
	
	@Override
	public Node getContent() {
		return content;
	}

	@Override
	public int getOrder() {
		return 10;
	}

	@Override
	public String getName() {
		return "其他";
	}

	@Override
	public String getMenuTabName() {
		return "主页";
	}

}
