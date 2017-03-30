package controller;

import com.jfinal.core.Controller;

/**
 * Created by yinshangwei on 30/03/2017.
 */
public class IndexController extends Controller {

    public void index() {
        render("index.jsp");
    }
}
