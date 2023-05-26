package com.wmy.scms.service.impl;

import com.wmy.scms.entity.Syslog;
import com.wmy.scms.entity.Team;
import com.wmy.scms.entity.User;
import com.wmy.scms.mapper.SyslogMapper;
import com.wmy.scms.mapper.TeamMapper;
import com.wmy.scms.mapper.UserMapper;
import com.wmy.scms.service.SyslogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 *
 * @since 2023-04-11
 */
@Service
public class SyslogServiceImpl implements SyslogService {
    @Autowired
    SyslogMapper syslogMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<Syslog> getAllSyslog(Page<Syslog> page, Syslog syslog) {
        Page<Syslog> syslogPage = syslogMapper.querySyslogBySyslogCondition(page, syslog);
        List<Syslog> syslogList = syslogPage.getRecords();
        for (Syslog s : syslogList) {
            s.setParameter(removeNullStr(s.getParameter()));
        }
        syslogPage.setRecords(syslogList);
        return syslogPage;
    }


    @Override
    public int addSyslog(Syslog syslog) {
        if (syslog == null) {
            return 0;
        } else {
            int effNum = syslogMapper.insertSyslog(syslog);
            if (effNum != 1) {
                return 0;
            } else {
                return effNum;
            }
        }
    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public boolean removeAllData() {
        //删除所有记录
        syslogMapper.deleteAllRecord();
        //删除所有排名
        syslogMapper.deleteAllRanking();
        //删除所有分数
        syslogMapper.deleteAllScore();
        //删除所有参赛运动员
        syslogMapper.deleteAllAthlete();
        //删除所有参赛项目
        syslogMapper.deleteAllItem();
        //删除所有系统日志
        syslogMapper.deleteAllSyslog();
        //删除所有用户
        syslogMapper.deleteAllUser();
        //删除所有团队
        syslogMapper.deleteAllTeam();
        //删除所有运动会
        syslogMapper.deleteAllSeason();
        //删除所有加油稿
        syslogMapper.deleteAllArticle();
        //删除所有世界杯项目获奖记录
        syslogMapper.deleteAllCup();
        //删除所有世界杯比赛记录
        syslogMapper.deleteAllRace();
        //删除所有获奖记录
        syslogMapper.deleteAllReward();
        //删除所有世界杯参赛记录
        syslogMapper.deleteAllWorld();

        //删除荣誉图片
        String tarImgPath = "D:\\A学习资料\\Java\\毕业设计\\scms_pic\\";
        File directory=new File(tarImgPath);
        File[] files = directory.listFiles();
        for(File f:files){
            if(!f.getName().equals("reward.png"))f.delete();
        }

        //添加root用户,清空数据后用于登录系统
        Team rootTeam = new Team();
        rootTeam.setTeamId(1);
        rootTeam.setTeamName("超级管理员");
        rootTeam.setCreateTime(LocalDateTime.now());
        rootTeam.setEditTime(LocalDateTime.now());
        teamMapper.insertTeam(rootTeam);

        Team normalTeam=new Team();
        normalTeam.setTeamId(2);
        normalTeam.setTeamName("东方");
        normalTeam.setCreateTime(LocalDateTime.now());
        normalTeam.setEditTime(LocalDateTime.now());
        teamMapper.insertTeam(normalTeam);

        Team judgeTeam=new Team();
        judgeTeam.setTeamId(3);
        judgeTeam.setTeamName("裁判团队");
        judgeTeam.setCreateTime(LocalDateTime.now());
        judgeTeam.setEditTime(LocalDateTime.now());
        teamMapper.insertTeam(judgeTeam);

        //添加默认管理员
        addNewUser(1,"admin","admin",1,rootTeam);

        //添加普通用户以及裁判
        addNewUser(2,"朝伟","chaowei",3,normalTeam);
        addNewUser(3,"德华","dehua",3,normalTeam);
        addNewUser(4,"学友","xueyou",3,normalTeam);
        addNewUser(5,"彦祖","yanzu",3,normalTeam);

        addNewUser(6,"A裁判","judge",2,judgeTeam);

        //删除所有系统日志
        syslogMapper.deleteAllSyslog();
        return true;
    }


    //重置后添加普通用户
    public void addNewUser(Integer id ,String nickName,String username,Integer type,Team team){
        User normal=new User();
        normal.setUserId(id);
        normal.setNickname(nickName);
        normal.setUsername(username);
        normal.setPassword(passwordEncoder.encode("123456"));
        normal.setUserSex("男");
        normal.setUserType(type);
        normal.setTeam(team);
        normal.setPhone("1322726XXXX");
        normal.setCreateTime(LocalDateTime.now());
        normal.setEditTime(LocalDateTime.now());
        userMapper.insertUser(normal);
    }

    //字符串去除null
    public String removeNullStr(String args) {
        String[] strings = args.split(",");
        String str = "";
        for (String s : strings) {
            if (!s.contains("null")) {
                str = str + s + ",";
            } else {
                if (s.contains(")")) {
                    str += ")";
                }
            }
        }
        return str;
    }
}
