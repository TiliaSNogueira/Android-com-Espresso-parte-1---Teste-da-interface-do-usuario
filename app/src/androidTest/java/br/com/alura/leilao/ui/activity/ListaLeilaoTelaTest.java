package br.com.alura.leilao.ui.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofi.client.TesteWebClient;
import br.com.alura.leilao.model.Leilao;

public class ListaLeilaoTelaTest {

    public static final String LEILAO_NAO_FOI_SALVO = "Leilão não foi salvo ";
    public static final String BANCO_DE_DADOS_NAO_FOI_LIMPO = "Banco de dados não foi limpo";
    @Rule
    public ActivityTestRule<LancesLeilaoActivity> activity = new ActivityTestRule<>(LancesLeilaoActivity.class, true, false);
    public final TesteWebClient webClient = new TesteWebClient();

    //before diz que vai sempre chamar esse metodo antes dos testes
    //vai limpar a base de dados
    @Before
    public void setup() {
        boolean bancoDeDadosNaoFoiLimpo = !webClient.limpaBancoDeDados();
        if (bancoDeDadosNaoFoiLimpo) {
            Assert.fail(BANCO_DE_DADOS_NAO_FOI_LIMPO);
        }
    }


    //metodo do espresso que busca a view que queremos verificar
    @Test
    public void deve_AparecerUmLeilao_QuandoCarregaUmLeilaoNaAPI() throws IOException {

        tentaSalvarLeilaoNaAPI(new Leilao("brigadeiro"));

        activity.launchActivity(new Intent());

        //primeiro verifica se o texto é igual ao que está entre parenteses
        //depois verifica se está sendo exibida
        onView(withText("brigadeiro"))
                .check(matches(isDisplayed()));
    }



    @Test
    public void deve_AparecerDoisLeiloes_QuandoCarregaDoisLeiloesDaAPI() throws IOException {

        tentaSalvarLeilaoNaAPI(new Leilao("sonho"), new Leilao("brigadeiro"));

        activity.launchActivity(new Intent());
        onView(withText("brigadeiro"))
                .check(matches(isDisplayed()));
        onView(withText("sonho"))
                .check(matches(isDisplayed()));
    }



    private void tentaSalvarLeilaoNaAPI(Leilao... leiloes) throws IOException {
       for(Leilao leilao : leiloes){
           Leilao leilaoSalvo = webClient.salva(leilao);
           if (leilaoSalvo == null) {
               Assert.fail(LEILAO_NAO_FOI_SALVO + leilao.getDescricao());
           }
       }

    }