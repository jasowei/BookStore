package com.lanou.bookstore.category.web.servlet.admin;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.domain.ImageResizer;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;
import com.lanou.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
    private AdminService service = new AdminServiceImpl();
    private BookService serviceB = new BookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /**
         * 添加图书
         * 上传三步：
             创建工厂
             创建解析器
             解析request得到表单字段！
         */
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            String bname = new String(fileItems.get(1).getString().getBytes("iso-8859-1"), "utf-8");
            String price = fileItems.get(2).getString();
            String author = new String(fileItems.get(3).getString().getBytes("iso-8859-1"), "utf-8");
            String cid = fileItems.get(4).getString();
            FileItem fi = fileItems.get(0);
            if (bname.equals("")||price.equals("")||author.equals("")||cid.equals("")||fi==null){
                request.setAttribute("msg","请填写完整!");
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request,response);
            }else {
                // 1. 得到文件保存路径
                String root = this.getServletContext().getRealPath("/WEB-INF/files/");
                // 2. 生成2层目录
                // > 1. 得到文件名
                // > 2. 得到 hashCode()
                // > 3. 转换成16进制
                // > 4. 获取前两个字符生成目录
                // 获取上传文件名
                String fileName = fi.getName();
                // 处理文件绝对路径问题
                int index = fileName.lastIndexOf("/");
                if (index != 1) {
                    fileName = fileName.substring(index + 1);
                }
                // 处理文件同名 : 加 uuid
                String savaName = CommonUtils.uuid() + "_" + fileName;
                // 4. 创建目录文件
                File destFile = new File(this.getServletContext().getRealPath("/book_img/"), savaName);
                // 5. 保存文件
                fi.write(destFile);
                // 调整尺寸
                ImageResizer imageResizer = new ImageResizer();
                String path = this.getServletContext().getRealPath("/book_img/") + savaName;
                imageResizer.resizeImage(path, path, 108, 150);
                /**
                 把表单字段封装到Book对象中
                 保存上传文件，把保存的路径设置给Book的image属性。
                 调用service方法保存Book对象到数据库中
                 调用findAll()
                 */
                Book book = new Book();
                book.setImage("book_img/" + savaName);
                book.setBname(bname);
                book.setAuthor(author);
                book.setCid(cid);
                book.setPrice(price);

                List<Book> books1 = serviceB.findAll2();
                book.setBid(String.valueOf(books1.size() + 1));

                book.setDel(false);
                service.addBook(book);
                List<Book> books = serviceB.findAll();

                request.setAttribute("books", books);
                //转发到/adminjsps/admin/book/list.jsp
                request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request, response);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}