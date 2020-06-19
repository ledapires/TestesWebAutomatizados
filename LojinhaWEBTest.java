package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LojinhaWEBTest {

    private WebDriver navegador;

    @Before
    public  void setUp(){
        //Preparação
        System.setProperty("webdriver.chrome.driver", "c:\\Temp\\drivers\\chromedriver.exe");
        navegador =  new ChromeDriver();
        navegador.get("http://165.227.93.41/lojinha-web/");

        // Login com usuário admin e senha admin
        navegador.findElement(By.cssSelector("#usuario")).sendKeys("admin");
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector(".btn")).click();
    }

    @Test
    public void testValidarDadosDeUmProduto(){
        // Acessar o produto PS4 na lista de Produtos
        navegador.findElements(By.linkText("PSP4")).get(0).click();

        // Validação do nome do Produto e do nome do primeiro Componente
       String produtonome =  navegador.findElement(By.cssSelector("#produtonome")).getAttribute("value");
        Assert.assertEquals("PSP4", produtonome);

    }

    @Test
    public void testAdicionarUmNovoProduto(){
        //Clica em adicionar um Produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Preencher os dados do Produto
        navegador.findElement(By.id("produtonome")).sendKeys("PSP11");
        navegador.findElement(By.id("produtovalor")).sendKeys("35000");
        navegador.findElement(By.id("produtocores")).sendKeys("Azul, Verde");
        navegador.findElements(By.cssSelector(".btn")).get(0).click();

        //validar que a mensagem de produto adicionado foi apresentada no toast
        String mensagem = navegador.findElement(By.cssSelector(".toast")).getText();
        Assert.assertEquals("Produto adicionado com sucesso", mensagem);
    }

    @After
    public void tearDown(){
        // Fechar o navegador
        navegador.quit();
    }
}
