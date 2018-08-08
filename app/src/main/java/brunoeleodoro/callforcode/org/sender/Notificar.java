package brunoeleodoro.callforcode.org.sender;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Notificar {

    public static void Notificar(Context context, MainActivity mainActivity)
    {
        /*
        {
  "data" : {
    "titulo":"Conteudo novo",
    "desc":"kfdjsalkfdsa"
  },
  "notification" : {
    "titulo":"Conteudo novo",
      "desc":"kfdjsalkfdsa"
  },
  "priority":"high",
  "to" : "/topics/all_vcsabia"
}

         */
        JSONObject tudo = new JSONObject();


        try {
            JSONObject data = new JSONObject();
            data.put("title","Tsunami acontecendo");
            data.put("desc", "Há um tsunami chegando, é importante que você vá para uma area segura.Clique em uma das opções abaixo");
            data.put("lat","-22.900086");
            data.put("lng","-47.196789");

            tudo.put("data", data);
            tudo.put("notification",data);
            tudo.put("priority","high");
            tudo.put("to", "/topics/callforcode");
        } catch (JSONException e) {
            Log.i("script", "erro="+e);
            e.printStackTrace();
        }
        Log.i("script", "object="+tudo.toString());

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.POST,"https://fcm.googleapis.com/fcm/send", tudo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("script","response="+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Script","error notificatino="+error);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> dados = new HashMap<>();
                dados.put("Content-Type", "application/json");
                dados.put("Authorization","key=AAAArWtmZH8:APA91bFDdDc7zpwzIC5r80i9COrKMvdjrg3MVCpcOgnrkYwYbtjh0SvNTYLCFsY6t_nMaoj1fbFFavnXIYe_b-0epmr3RoZP_dHoNzIdQ6FSWEoHCjOhpDBuOJWqzvc-GUP_HBFqNzCf");
                return dados;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };
        queue.add(request);
    }

}
