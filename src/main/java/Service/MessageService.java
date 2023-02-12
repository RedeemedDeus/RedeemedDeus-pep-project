package Service;

import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;
import Model.Account;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }


    /*
     * ADDS A MESSAGE TO THE DATABASE (SERVICE)
     */
    public Message addMessage(Message message){
        Account accountFromDb = this.accountDAO.getAccountById(message.posted_by);

        if(message.getMessage_text() == "") return null;
        if(message.getMessage_text().length() > 255) return null;
        if(accountFromDb == null) return null;

        Message newMessage = this.messageDAO.insertMessage(message);
        return newMessage;
    }

    /*
     * GETS ALL MESSAGES
     */
    public List<Message> getAllMessages(){
        List<Message> allMessages = this.messageDAO.getAllMessages();

        if(allMessages == null) return null;
        return allMessages;
    }

    /*
     * GET A MESSAGE BY ITS ID
     */
    public Message getMessageFromId(String message_id){
        Message messageFromDb = this.messageDAO.getMessageFromId(Integer.parseInt(message_id));

        if(messageFromDb == null) return null;
        return messageFromDb;
    }


    /*
     * DELETE A MESSAGE BY ITS MESSAGE_ID
     */
    public Message deleteMessageFromId(String message_id){
        Message messageFromDb = this.messageDAO.getMessageFromId(Integer.parseInt(message_id));

        if(messageFromDb == null) return null;

        this.messageDAO.deleteMessageFromId(Integer.parseInt(message_id));
        return messageFromDb;
    }


    /*
     * UPDATE MESSAGE BY IT'S MESSAGE_ID
     */
    public Message updateMessage(int message_id, String message_text){
        Message messageFromDb = this.messageDAO.getMessageFromId(message_id);

        if(message_text == "" || message_text.length() > 255) return null;
        if(messageFromDb == null) return null;

        this.messageDAO.updateMessage(message_id, message_text);
        messageFromDb = this.messageDAO.getMessageFromId(message_id);
        return messageFromDb;
    }


    /*
     * GET ALL MESSAGES FOR A PARTICULAR USER ID
     */
    public List<Message> getAllMessagesForAccountId(int account_id){
        List<Message> allmessages = this.messageDAO.getAllMessagesForAccountId(account_id);
        return allmessages;
    }
    
}
