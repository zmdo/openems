package com.schenchi.ems.platform.frame.menu;

import java.io.IOException;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.schenchi.ems.Main;
import com.tricolorfire.labfx.control.MenuTabPane;
import com.tricolorfire.labfx.control.PenetrablePane;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CommonActionBar extends PenetrablePane  {
	
	private HBox container; // 容器
	private HBox innerPane; // 面板具体内容
	
	private MenuTabPane menu;
	
	public CommonActionBar(MenuTabPane menu) {
		container = new HBox();
		innerPane = new HBox();
		
		this.menu = menu;
		this.translateXProperty().bind(
			menu.widthProperty().subtract(container.widthProperty()).divide(2)
		);		
		this.setTranslateY(-51);
		this.container.getChildren().addAll(innerPane,initFixedTabButton());
		this.getChildren().add(container);
	}

	
	private Node initFixedTabButton() {
		
		// 加载图片
		SVGGlyph upSvg = null,downSvg = null;
		try {
			upSvg = SVGGlyphLoader.loadGlyph(Main.class.getResource("/images/1-expand-down.svg"));
			downSvg = SVGGlyphLoader.loadGlyph(Main.class.getResource("/images/2-expand-up.svg"));
			upSvg.setFill(Color.YELLOW);
			downSvg.setFill(Color.GAINSBORO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final SVGGlyph upIcon,downIcon;
		if (upSvg != null) {
			upIcon = upSvg;
			downIcon = downSvg;
		} else {
			upIcon = null;
			downIcon = null;
		}
		
		// 初始化按钮
		Label fixedTabButton = new Label();
		fixedTabButton.setPrefWidth(24);
		fixedTabButton.setPrefHeight(25);
		fixedTabButton.setPadding(new Insets(4.5, 4, 4.5, 4));
		fixedTabButton.setGraphic(downIcon);
		
		// 初始化按钮监听器
		fixedTabButton.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			
			private boolean show = true;
			private final Rectangle MASK = new Rectangle(0,0,7680,27);
			
			@Override
			public void handle(MouseEvent event) {
				show = !show;
				if (show) {
					fixedTabButton.setGraphic(downIcon);
					menu.setClip(null);
					menu.setMaxHeight(Double.MAX_VALUE);
					setTranslateY(-51);
				} else {
					fixedTabButton.setGraphic(upIcon);
					menu.setClip(MASK);
					menu.setMaxHeight(27);
					setTranslateY(-1);
				}
			}
		});
		
		return fixedTabButton;
	}
	
	public HBox getHBox() {
		return innerPane;
	}
	
	/**
	 * 添加控件
	 * @param node
	 */
	public void addControl(Node node) {
		innerPane.getChildren().add(node);
	}
	
	public void addAllControls(Node...nodes) {
		innerPane.getChildren().addAll(nodes);
	}
}
