package com.plaksin.quoteservice.service.impl;


import com.plaksin.quoteservice.exception.EntityNotFoundException;
import com.plaksin.quoteservice.model.Quote;
import com.plaksin.quoteservice.repository.QuoteRepository;
import com.plaksin.quoteservice.service.QuoteService;
import lombok.RequiredArgsConstructor;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

//import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    public final QuoteRepository quoteRepository;


    @Override
    public Quote saveOrUpdateQuote(Quote quote) {
        quote.setCreatOrUpdateTime(LocalDateTime.now().withNano(0));
            return quoteRepository.save(quote);
    }

    @Override
    public Optional<Quote> getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId);
    }

    @Override
    public Quote getRandomQuote() {
        List<Long> idList = quoteRepository.findAllId();
        Long randomId = idList.stream()
                .skip((int) (idList.size() * Math.random()))
                .findAny().orElseThrow();
        return quoteRepository.findById(randomId).get();
    }

    @Override
    public void deleteQuoteById(Long quoteId) {
        try {
            quoteRepository.deleteById(quoteId);
        } catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Quote not exist in data base");
        }
    }

//    public void getGraphEvolution() {
//        XYSeries series = new XYSeries("Evolution");
//
////        for(float i = 0; i < Math.PI; i+=0.1){
////            series.add(i, Math.sin(i));
////        }
//
//        XYDataset xyDataset = new XYSeriesCollection(series);
//        JFreeChart chart = ChartFactory
//                .createXYLineChart("Что-то", "Time", "Rating",
//                        xyDataset,
//                        PlotOrientation.VERTICAL,
//                        true, true, true);
//        JFrame frame =
//                new JFrame("MinimalStaticChart");
//        // Помещаем график на фрейм
//        frame.getContentPane()
//                .add(new ChartPanel(chart));
//        frame.setSize(600,450);
//        frame.setVisible(true);
//    }


    @Override
    public List<Quote> getTopQuotes() {
        return quoteRepository.findTopQuotes();
    }
}
