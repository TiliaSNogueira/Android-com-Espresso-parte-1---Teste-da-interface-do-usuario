package br.com.alura.leilao.ui.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class ListaLeilaoTelaTest {

    @Rule
    public ActivityTestRule<LancesLeilaoActivity> activity = new ActivityTestRule<>(LancesLeilaoActivity.class, true, true);

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregaUmLeilaoNaAPI(){
        //metodo do espresso que busca a view que queremos verificar
        //primeiro verifica se o texto é igual ao que está entre parenteses
        //depois verifica se está sendo exibida
        onView(withText("brigadeiro"))
                .check(matches(isDisplayed()));
    }

}