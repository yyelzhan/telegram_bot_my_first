

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class CourseBot extends TelegramLongPollingBot {

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            setButton(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();



            if (message != null && message.hasText()) {
            String text = message.getText();
            if ("/ru".equalsIgnoreCase(text)) {
                sendMsg(message, "1 Российский рубль равно\n" +
                        "5,82");
            }else if ("/usd".equalsIgnoreCase(text)) {
                sendMsg(message, "1 Доллар США равно\n" +
                        "436,24");
            }
            else  if("/euro".equalsIgnoreCase(text)) {

                sendMsg(message, "1 Евро равно\n" +
                        "481,32");
            }

            } else  {
                sendMsg(message,"Достпуно только RU,EURO,USD");
            }
        }


        public void setButton (SendMessage sendMessage){
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);

            List<KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow keyboardFirstRow = new KeyboardRow();

            keyboardFirstRow.add(new KeyboardButton("/RU"));
            keyboardFirstRow.add(new KeyboardButton("/USD"));
            keyboardFirstRow.add(new KeyboardButton("/EURO"));
            keyboardRowList.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboardRowList);

        }


    public String getBotUsername() {
        return "course bot";
    }

    public String getBotToken() {
        return "940683490:AAEQ6alqIblzwSSzCa3FaYZFD24afLpkPTE";
    }
}
