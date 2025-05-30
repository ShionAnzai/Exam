package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;



public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

     // ローカル変数の設定
        String student_no = ""; // 入力された学番

        String student_name = ""; // 生徒名


        // リクエストパラメータの取得(入力されたガクバンを代入)

        student_no = req.getParameter("studentNumber");

        //検索対象の学生の情報をdaoを使ってDBからとってくる
        StudentDao stuDao = new StudentDao();
        Student student = stuDao.get(student_no); // ← 学生番号で検索

        if (student != null) {
            student_name = student.getName(); // 氏名を取得
            System.out.println("氏名：" + student_name);
        } else {
            System.out.println("該当する学生はいません");
        }

        //TestListStudentDaoから、学生番号をもとに検索し成績一覧としてリスト化したものを受け取る
        TestListStudentDao tlsDao = new TestListStudentDao();
        List<TestListStudent> testList = tlsDao.filter(student);


        req.setAttribute("student", student);
        req.setAttribute("testList", testList);

        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);



    }
}