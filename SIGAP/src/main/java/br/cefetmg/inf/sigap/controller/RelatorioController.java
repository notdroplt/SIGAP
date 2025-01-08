/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.inf.sigap.controller;

/**
 *
 * @author luisg
 */

import br.cefetmg.inf.sigap.db.RelatorioItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/relatorio")
public class RelatorioController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataInicio = req.getParameter("dataInicio");
        String dataFim = req.getParameter("dataFim");

        if (dataInicio != null && dataFim != null) {
            req.setAttribute("itens", RelatorioItemService.listarItensPorPeriodo(dataInicio, dataFim));
        } else {
            req.setAttribute("itens", RelatorioItemService.listarItens());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/relatorios/relatorioItens.jsp");
        dispatcher.forward(req, resp);
    }
}

