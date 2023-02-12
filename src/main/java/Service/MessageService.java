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
     * DELETE A MESSAGE BY ITS ID
     */
    public Message deleteMessageFromId(String message_id){
        Message messageFromDb = this.messageDAO.getMessageFromId(Integer.parseInt(message_id));

        if(messageFromDb == null) return null;

        this.messageDAO.deleteMessageFromId(Integer.parseInt(message_id));
        return messageFromDb;
    }
    
}
