package jp.co.kiramex.dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import jp.co.kiramex.dbSample.dao.CountryDAO;
import jp.co.kiramex.dbSample.entity.Country;

public class DbConnectSample06 {

    public static void main(String[] args) {
        // Countryクラスにアクセスするため、CountryDaoをインスタンス化
        CountryDAO dao = new CountryDAO();

        // 検索用キーワードを入力
        System.out.print("検索キーワードを入力してください >");
        String name = keyIn().trim();

        // 入力された値を引数に指定し、検索結果を実行し、Listオブジェクトを取得
        List<Country> list = dao.getCountryFromName(name);

        // 取得したListオブジェクトを順番に取り出し、出力
        for(Country item : list) {
            System.out.println(item.getName());
            System.out.println(item.getPopulation());
        }
    }

    /*
     * キーボードから入力された値をStringで返す　引数：なし　戻り値：入力された文字列
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
