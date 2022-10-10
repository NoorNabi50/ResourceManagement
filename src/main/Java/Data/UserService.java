package Data;

import Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Transactional
public class UserService {

    @Autowired
    private HibernateTemplate template;


    public Boolean Login(User user)
    {

        try {
            List<User> users = this.template.loadAll(User.class);
            if (users.size() > 0)
            {
                User u = users.get(0);
               if(u.getUserName() == user.getUserName() && VerifyPassword(u.getUPassword(),user.getUPassword()))
                   return true;
               return false;

            }
            template.save(user);
            return true;
   }

        catch(Exception e)
        {
            return  false;
        }
    }


    private String GeneratePassword() throws NoSuchAlgorithmException {
        String Upass = "randompasshere";
        MessageDigest md = MessageDigest.getInstance("SHA-1") ;
       // md.update("can add random salt byte type");
        return  md.digest(Upass.getBytes()).toString();
    }

    private Boolean VerifyPassword(String PasswordHash,String Upass) throws NoSuchAlgorithmException {
        MessageDigest md =  MessageDigest.getInstance("SHA-1");
        return PasswordHash == md.digest(Upass.getBytes()).toString();
    }

}
