package config;

import cn.dreampie.quartz.QuartzKey;
import cn.dreampie.quartz.QuartzPlugin;
import cn.dreampie.quartz.job.QuartzCronJob;
import cn.dreampie.quartz.job.QuartzOnceJob;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import controller.IndexController;
import job.DemoJob;

import java.util.Date;

/**
 * Created by yinshangwei on 30/03/2017.
 */
public class AppConfig extends JFinalConfig {

    public static void main(String[] args) {
        /**
         * 特别注意：Eclipse 之下建议的启动方式
         */
        // JFinal.start("WebRoot", 80, "/", 5);

        /**
         * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
         */
        JFinal.start("src/main/webapp", 8080, "/");
    }

    @Override
    public void configConstant(Constants me) {
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode", false));
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class, "/");    // 第三个参数为该Controller的视图存放路径
        // me.add("/blog", BlogController.class);	    // 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        // 加载QuartzPlugin
        me.add(new QuartzPlugin());
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }

    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();

        // run cron
        new QuartzCronJob(new QuartzKey(1, "DemoJob", "DemoJob"), "*/5 * * * * ?", DemoJob.class)
                .addParam("name", "run cron").start();

        //run once
        new QuartzOnceJob(new QuartzKey(2, "DemoJob", "DemoJob"), new Date(), DemoJob.class)
                .addParam("name", "run once").start();
    }
}
