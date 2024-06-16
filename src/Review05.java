
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        // データベース接続と結果取得のための変数宣言
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            //  ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "pw06100!"
                    );
            
            // DBとやりとりする窓口(PreparedStatementオブジェクト)の作成
            String sql = "SELECT * FROM person where id = ?";
            pstmt = con.prepareStatement(sql);
            
            // Select文の実行と結果を格納/代入
            System.out.print("検索キーワードを入力して下さい > ");
            String input = keyIn();
            
            // PreparedStatementオブジェクトの?に値をセット
            pstmt.setString(1, input);
            rs = pstmt.executeQuery();
            
            // 結果を表示する
            while(rs.next()) {
                // Name列の値を取得
                String name = rs.getString("Name");
                // age列の値を取得
                int age = rs.getInt("age");
                
                // 取得した値を表示
                System.out.println(name);
                System.out.println(age);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました");
            e.printStackTrace();
        } finally {
            // 接続を閉じる
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("ResultSetを閉じる時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("PreparedStatementを閉じる時にエラーが発生しました");
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }

    }
    
    /*
     *  キーボードから入力された値をStringで返す　引数：なし　戻り値：なし
     */ 
     private static String keyIn() {
         String line = null;
         try {
             BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
             line = key.readLine();
         } catch (IOException e) {
             
         } return line;
     }
}
