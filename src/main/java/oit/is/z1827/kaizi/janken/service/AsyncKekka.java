package oit.is.z1827.kaizi.janken.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1827.kaizi.janken.model.*;

@Service
public class AsyncKekka {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchmapper;
  @Autowired
  MatchinfoMapper matchinfomapper;

  public ArrayList<Match> synactivematchs() {
    return matchmapper.selectisActivematch();
  }

  @Async
  public void asyncactivematches(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true){
        ArrayList<Match> activematch = this.synactivematchs();
        emitter.send(activematch);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
      ArrayList<Match> activematch = this.synactivematchs();
      matchmapper.updatebyisactive(activematch);
    }
  }

}
