package learn.core2.ch09modules.analyzer;

import learn.core2.ch09modules.openpkg.Country;

public class Demo {
    public static void main(String[] args) {
        Country belgium = new Country("Belgium", 30510);
        var analyzer = new ObjectAnalyzer();
        System.out.println(analyzer.analyze(belgium));
    }
}
