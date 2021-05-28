package me.petulikan1.minecrafttodiscord;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DiscordMessage implements Listener {
    protected static String wh=Loader.fc.getString("Webhook");
    @EventHandler

    public static void onplayermsg(AsyncPlayerChatEvent e){
        if(wh.equalsIgnoreCase("PUT_HERE_WEB_HOOK")){
            return;
        }
        String message = Loader.fc.getString("Prefix")!=null?Loader.fc.getString(""):e.getPlayer().getName() + " » "+e.getMessage();
        sendmessage(message);
    }
    private static void sendmessage(String message){
        PrintWriter out=null;
        BufferedReader in=null;
        StringBuilder result=null;
        try {
            URL rURL = new URL(wh);
            URLConnection conn = rURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            String postData = URLEncoder.encode("content","UTF-8")+"="+URLEncoder.encode(message,"UTF-8");
            out.print(postData);
            out.flush();
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!=null){
                result.append("/n").append(line);
            }
        }catch (Exception e){e.printStackTrace();} finally {
            try {
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch (Exception e){
            }
        }
    }
}
