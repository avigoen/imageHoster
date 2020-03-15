package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/images/{imageId}/{title}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable Integer imageId, @PathVariable String title, Comment comment, HttpSession session) {
        Image image = imageService.getImage(imageId);
        List<Comment> commentList = new ArrayList<>();
        User user = (User) session.getAttribute("loggeduser");
        comment.setUser(user);
        commentList.add(comment);
        image.setComments(commentList);
        imageService.updateImage(image);
        return "redirect:/images/{imageId}/{title}";
    }
}
