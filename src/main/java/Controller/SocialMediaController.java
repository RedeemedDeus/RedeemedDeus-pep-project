package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler); //DONE
        app.post("/login", this::postLoginHandler);       //DONE
        app.post("/messages", this::postMessageHandler);  //DONE
        app.get("/messages", this::getAllMessagesHandler);//DONE
        app.get("/messages/{message_id}", this::exampleHandler);
        app.delete("/messages/{message_id}", this::exampleHandler);
        app.patch("/messages/{message_id}", this::exampleHandler);
        app.get("/accounts/{account_id}/messages", this::exampleHandler);
        

        return app;
    }



    /**
     * Handler to register a new user (post a new registration)
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);

        if(addedAccount==null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(addedAccount));
        }
    }

    /**
     * Handler to login a returning user(post a login request)
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postLoginHandler(Context context)throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account postedLogin = accountService.login(account.getUsername(), account.getPassword());

        if(postedLogin==null){
            context.status(401); //Login was not successful, therefore status code 401(Unauthorized)
        }else{
            context.json(mapper.writeValueAsString(postedLogin));
        } 
    }

    /**
     * Handler to post a new message(post a message)
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            context.json(mapper.writeValueAsString(addedMessage));
        }else{
            context.status(400);
        }
    }

    /**
     * Handler to get all messages(get messages)
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    









    
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

}