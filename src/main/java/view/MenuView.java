package view;


import controller.localControler.Command;
import controller.localControler.ReplayConversation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author francoise.perrin
 * Cette classe est le menu du jeu d'échec
 */
public class MenuView extends MenuBar {

	ReplayConversation replayConversation = new ReplayConversation(new Command() {
		@Override
		public void execute() {
			GuiConfig.setInitState();
		}
	});

	public MenuView () {
		super();
		this.getMenus().add(newMenuStyle());
		this.getMenus().add(newMenuColor());
		this.getMenus().add(newMenuEdit());
	}

	private Menu newMenuColor () {

		Menu menu = new Menu("Couleur d'affichage");
		MenuItem color1 = new MenuItem("Couleur cases blanches");
		MenuItem color2 = new MenuItem("Couleur cases noires");

		color1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {

				Dialog<Void> colorDialog = new Dialog<>();
				colorDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

				ColorPicker colorPicker = new ColorPicker(GuiConfig.whiteSquareColor.get());
				colorPicker.setOnAction(colorEvent -> {
					replayConversation.exec(new Command() {
						@Override
						public void execute() {
							GuiConfig.whiteSquareColor.set(colorPicker.getValue());
						}
					});


					colorDialog.close();
				});
				colorDialog.getDialogPane().setContent(colorPicker);
				colorDialog.show();
			}
		});

		color2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {

				Dialog<Void> colorDialog = new Dialog<>();
				colorDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

				ColorPicker colorPicker = new ColorPicker(GuiConfig.blackSquareColor.get());
				colorPicker.setOnAction(colorEvent -> {

					replayConversation.exec(new Command() {
						@Override
						public void execute() {
							GuiConfig.blackSquareColor.set(colorPicker.getValue());
						}
					});


					colorDialog.close();
				});

				colorDialog.getDialogPane().setContent(colorPicker);
				colorDialog.show();
			}
		});

		menu.getItems().addAll(color1, color2);
		return menu;
	}

	private Menu newMenuStyle () {

		Menu menu = new Menu("Style d'affichage");
		RadioMenuItem style1 = new RadioMenuItem("Dégradé");
		RadioMenuItem style2 = new RadioMenuItem("Uni");
		ToggleGroup btnGroup = new ToggleGroup();

		style1.setToggleGroup(btnGroup);
		style2.setToggleGroup(btnGroup);

		Map<MenuItem, PaintStyle> btnMap = new HashMap<>();
		btnMap.put(style1, PaintStyle.GRADIENT);
		btnMap.put(style2, PaintStyle.SOLID);


		style1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				 replayConversation.exec(new Command() {
					 @Override
					 public void execute() {
						 GuiConfig.paintStyle.set(PaintStyle.GRADIENT);
						 Object style = GuiConfig.paintStyle.get();
						 btnMap.forEach((menuItem, paintstyle) -> {
							 if (paintstyle.equals(style)) {
								 ((RadioMenuItem) menuItem).setSelected(true);
							 }
						 });
					 }
				 });
			}
		});

		style2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				replayConversation.exec(new Command() {
					@Override
					public void execute() {
						GuiConfig.paintStyle.set(PaintStyle.SOLID);
					}
				});
			}
		});

		menu.getItems().addAll(style1, style2);
		return menu;
	}

	private Menu newMenuEdit () {

		Menu menu = new Menu("Gestion Commandes");
		MenuItem undo = new MenuItem("Undo");
		MenuItem redo = new MenuItem("Redo");

		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				replayConversation.undo();
			}
		});

		redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				replayConversation.redo();
			}
		});

		menu.getItems().addAll(undo, redo);
		return menu;
	}


}
