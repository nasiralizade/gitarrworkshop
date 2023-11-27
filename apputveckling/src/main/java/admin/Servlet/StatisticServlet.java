package admin.Servlet;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", urlPatterns = "/myservlet")
public class StatisticServlet extends HttpServlet {
    public StatisticServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObject json = Json.createObjectBuilder()
                .add("month", "January")
                .add("income", 1000000)
                .add("month", "February")
                .add("income", 2000000)
                .add("month", "March")
                .add("income", 3000000)
                .add("month", "April")
                .add("income", 4000000)
                .add("month", "May")
                .add("income", 5000000)
                .add("month", "June")
                .add("income", 6000000)
                .add("month", "July")
                .add("income", 7000000)
                .add("month", "August")
                .add("income", 8000000)
                .add("month", "September")
                .add("income", 9000000)
                .add("month", "October")
                .add("income", 10000000)
                .add("month", "November")
                .add("income", 11000000)
                .add("month", "December")
                .add("income", 12000000)
                .build();
        arrayBuilder.add(json);
        PrintWriter out = response.getWriter();
        out.println(arrayBuilder.build().toString());
        out.close();
    }

}

