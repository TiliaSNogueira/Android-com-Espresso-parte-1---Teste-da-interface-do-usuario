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

    @Rule
    public ActivityTestRule<LancesLeilaoActivity> activity = new ActivityTestRule<>(LancesLeilaoActivity.class, true, false);

    //metodo do espresso que busca a view que queremos verificar
    @Test
    public void deve_AparecerUmLeilao_QuandoCarregaUmLeilaoNaAPI() throws IOException {
         //preparando o cenario

        TesteWebClient webClient = new TesteWebClient();

        boolean bancoDeDadosNaoFoiLimpo = !webClient.limpaBancoDeDados();
        if(bancoDeDadosNaoFoiLimpo){
            Assert.fail("Banco de dados não foi " +
                    "limpo");
        }


        Leilao brigadeiroSalvo = webClient.salva(new Leilao("brigadeiro"));
        if(brigadeiroSalvo == null) {
            Assert.fail("Leilão não foi salvo");
        }

        activity.launchActivity(new Intent());

        //primeiro verifica se o texto é igual ao que está entre parenteses
        //depois verifica se está sendo exibida
        onView(withText("brigadeiro"))
                .check(matches(isDisplayed()));
    }



}