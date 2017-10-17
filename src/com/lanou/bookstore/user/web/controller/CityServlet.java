package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.user.domain.China;
import net.sf.json.JSONArray;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/9/28.
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        List<China> chinas = ana();
        JSONArray array = JSONArray.fromObject(chinas);
        response.getWriter().print(array.toString());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }

    public List<China> ana() {

        List<China> list = new ArrayList<>();

        File file = new File(this.getServletContext().getRealPath("/WEB-INF/china.xml"));
        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(file);

            Element root = document.getRootElement();

            List<Element> elements = root.elements();

            for (Element element : elements) {
                China china = new China();
                china.setProvince(element.attributeValue("name"));
                List<Element> subElms = element.elements();
                List<String> city = new ArrayList<>();
                for (Element elm : subElms) {
                    city.add(elm.getTextTrim());
                }
                china.setCity(city);
                list.add(china);
            }

            return list;

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}