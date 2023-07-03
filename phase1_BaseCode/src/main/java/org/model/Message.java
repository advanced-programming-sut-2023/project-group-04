package org.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Calendar;

public class Message {
    private HBox hBox;
    private String sender;
    private String content;
    private String seenStatus;
    private String time;
    private int id;
    private boolean isSelected;

    public Message(String sender, String content, int id) {
        this.sender = sender;
        this.content = content;
        this.id = id;
        this.seenStatus = "sent";
        setTime();
        createHBox();
    }

    private void createHBox() {
        HBox hBox1 = new HBox();
        ImageView imageView = new ImageView(new Image(Player.getPlayerByUsername(sender).getAvatarResource()));
        Text text1 = new Text(sender + ":");
        Text text = new Text(content + ".");
        Text text2 = new Text(time + "   ");
        Text text3 = new Text();
        if (seenStatus.equals("sent"))
            text3.setText("*");
        else text3.setText("**");
        hBox1.getChildren().addAll(imageView, text1, text, text2, text3);
        hBox1.setSpacing(20);
        hBox = hBox1;
    }

    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String mmTime, hhTime;
        if (minute < 10) mmTime = "0" + minute;
        else mmTime = Integer.toString(minute);
        if (hour < 10) hhTime = "0" + hour;
        else hhTime = Integer.toString(hour);
        this.time = hhTime + ":" + mmTime;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void changeSelected() {
        isSelected = !isSelected;
    }
}
