package common.net.volley.toolbox;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import common.log.DebugLog;
import common.net.volley.AuthFailureError;
import common.net.volley.NetworkResponse;
import common.net.volley.ParseError;
import common.net.volley.Request;
import common.net.volley.Response;

/**
 * Created by li on 2015/5/26.
 */
public class PostFormRequest<T> extends Request<T> {

    private Response.Listener mListener ;
    private Type mClazz;

    private List<FormText> mListItem ;

    private final static String BOUNDARY = "---------8888888888888"; //数据分隔线
    private final static String MULTIPART_FORM_DATA = "multipart/form-data";

    public PostFormRequest(String url, List<FormText> listItem, Type type, Response.Listener listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mListener = listener ;
        mClazz = type ;
        setShouldCache(false);
        mListItem = listItem ;
    }

    /**
     * 这里开始解析数据
     * @param response Response from the network
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result ;
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            DebugLog.v("response:" + jsonString);
            result = JSONObject.parseObject(jsonString, mClazz);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 回调正确的数据
     * @param response The parsed response returned by
     */
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (mListItem == null|| mListItem.size() == 0){
            return super.getBody() ;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        int N = mListItem.size() ;
        FormText formText ;
        for (int i = 0; i < N ;i++){
            formText = mListItem.get(i) ;
            StringBuffer sb= new StringBuffer() ;
            /*第一行:"--" + boundary + "\r\n" ;*/
            sb.append("--"+BOUNDARY);
            sb.append("\r\n") ;
            /*第二行:"Content-Disposition: form-data; name="参数的名称"" + "\r\n" ;*/
            sb.append("Content-Disposition: form-data;");
            sb.append("name=\"");
            sb.append(formText.getName()) ;
            sb.append("\"") ;
            sb.append("\r\n") ;
            /*第三行:"\r\n" ;*/
            sb.append("\r\n") ;
            /*第四行:"参数的值" + "\r\n" ;*/
            sb.append(formText.getValue()) ;
            sb.append("\r\n") ;
            try {
                bos.write(sb.toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*结尾行:"--" + boundary + "--" + "\r\n" ;*/
        String endLine = "--" + BOUNDARY + "--"+ "\r\n" ;
        try {
            bos.write(endLine.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    /*获取内容类型，这里为表单类型*/
    @Override
    public String getBodyContentType() {
        return MULTIPART_FORM_DATA+"; boundary="+BOUNDARY;
    }
}
