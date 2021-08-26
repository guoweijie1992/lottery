package com.hzsmk.talentcode.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 人才认定类型
 *
 * @author jiangjh
 * @date 2020/7/9 10:13
 */
public class TalentUserType {

    public static final String TECHNOLOGY_TYPE = "科技人才";
    public static final String EDUCATION_TYPE = "教育卫生人才";
    public static final String MANAGE_TYPE = "经营管理人才";
    public static final String SKILL_TYPE = "技能人才";
    public static final String CULTURE_TYPE = "文化艺术人才";
    public static final String OTHER_TYPE = "其他人才";


    public static Map<String, String> userTypeMap = new HashMap<>();

    static {
        userTypeMap.put("诺贝尔奖获得者", "科技人才");
        userTypeMap.put("国家“万人计划”杰出人才人选", "科技人才");
        userTypeMap.put("菲尔兹奖获得者", "科技人才");
        userTypeMap.put("图灵奖获得者", "科技人才");
        userTypeMap.put("国家最高科学技术奖获得者", "科技人才");
        userTypeMap.put("中国科学院院士", "科技人才");
        userTypeMap.put("中国工程院院士", "科技人才");
        userTypeMap.put("普利兹克奖获得者", "科技人才");
        userTypeMap.put("中国社会科学院学部委员、荣誉学部委员", "科技人才");
        userTypeMap.put("相当于A类层次的顶尖人才", "其他人才");
        userTypeMap.put("国家“万人计划”中除杰出人才之外的人选", "科技人才");
        userTypeMap.put("浙江省“万人计划”杰出人才人选", "科技人才");
        userTypeMap.put("国家有突出贡献的中青年专家", "科技人才");
        userTypeMap.put("国务院批准的享受政府特殊津贴的专家", "科技人才");
        userTypeMap.put("百千万人才工程国家级人选", "科技人才");
        userTypeMap.put("中华技能大奖获得者", "技能人才");
        userTypeMap.put("国家级技能大师工作师领衔人", "技能人才");
        userTypeMap.put("功勋飞行员", "其他人才");
        userTypeMap.put("国家级海外高层次人才", "科技人才");
        userTypeMap.put("中国政府“友谊奖”获得者", "其他人才");
        userTypeMap.put("浙江省特级专家", "科技人才");
        userTypeMap.put("国家杰出青年基金项目完成人", "科技人才");
        userTypeMap.put("国家自然科学奖、国家技术发明奖、国家科学技术进步奖一等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("浙江省科学技术重大贡献奖获得者", "科技人才");
        userTypeMap.put("国家科技重大专项技术总师", "科技人才");
        userTypeMap.put("近5年来，担任并完成国家自然科学基金重大项目第一负责人", "科技人才");
        userTypeMap.put("中国青年女科学家奖获得者", "科技人才");
        userTypeMap.put("中国青年科技奖获得者", "科技人才");
        userTypeMap.put("“长江学者奖励计划”教授", "教育卫生人才");
        userTypeMap.put("国家级教学名师", "教育卫生人才");
        userTypeMap.put("国家级教学成果奖特等奖获得者（前3位完成人）", "教育卫生人才");
        userTypeMap.put("全国文化名家暨宣传文化系统“四个一批”人才", "文化艺术人才");
        userTypeMap.put("全国文化企业30强企业主要经营管理人才（指总公司董事长、总经理）", "经营管理人才");
        userTypeMap.put("国家级非物质文化遗产传承人", "文化艺术人才");
        userTypeMap.put("茅盾文学奖或鲁迅文学奖获得者", "文化艺术人才");
        userTypeMap.put("国医大师", "教育卫生人才");
        userTypeMap.put("国家级医学会专业委员会主任、副主任", "教育卫生人才");
        userTypeMap.put("国家级名中医", "教育卫生人才");
        userTypeMap.put("中国工艺美术大师", "文化艺术人才");
        userTypeMap.put("世界500强企业主要经营管理人才（指总公司董事长、总经理）", "经营管理人才");
        userTypeMap.put("年销售收入50亿元以上集成电路企业高级管理人才和技术研发骨干，且年工资性收入在100万元以上", "科技人才");
        userTypeMap.put("年纳税5亿元以上制造业(或国家级高新技术企业)主要经营管理人才（指公司董事长、总经理），上年度工资性收入在200万元以上。", "经营管理人才");
        userTypeMap.put("全国工程勘察设计大师", "科技人才");
        userTypeMap.put("梁思成奖获得者", "科技人才");
        userTypeMap.put("世界行业排名前10位的金融机构总部的高管、首席风险控制人员、首席财务管理人员、首席产品管理人员、首席技术人员及首席经济学家", "经营管理人才");
        userTypeMap.put("中国标准创新贡献奖获得者（个人奖）", "科技人才");
        userTypeMap.put("相当于B类层次的领军人才", "其他人才");
        userTypeMap.put("省级海外高层次人才", "科技人才");
        userTypeMap.put("省“万人计划”中除杰出人才之外的人选", "科技人才");
        userTypeMap.put("杭州市杰出人才和杰出青年人才", "科技人才");
        userTypeMap.put("杭州市级海外高层次人才", "科技人才");
        userTypeMap.put("省有突出贡献的中青年专家", "科技人才");
        userTypeMap.put("享受省政府特殊津贴专家", "其他人才");
        userTypeMap.put("通过综合考评的浙江省“151”人才工程重点资助和第一层次培养人选", "科技人才");
        userTypeMap.put("全国技术能手", "技能人才");
        userTypeMap.put("钱江技能大奖获得者", "技能人才");
        userTypeMap.put("省首席技师", "技能人才");
        userTypeMap.put("国家技能人才培育突出贡献奖获得者", "技能人才");
        userTypeMap.put("世界技能大赛金牌获得者", "技能人才");
        userTypeMap.put("省级技能大师工作室领衔人", "技能人才");
        userTypeMap.put("国家级技能大师工作室领衔人", "技能人才");
        userTypeMap.put("浙江省“百千万“高技能领军人才（杰出技能人才）", "技能人才");
        userTypeMap.put("培养产生世界技能大赛获奖选手的专家", "技能人才");
        userTypeMap.put("获安全飞行金质奖章飞行员", "技能人才");
        userTypeMap.put("浙江省政府“西湖友谊奖”获得者", "其他人才");
        userTypeMap.put("国家自然科学奖、国家技术发明奖、国家科学技术进步奖二等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("省（部）自然科学奖、技术发明奖、科学技术进步奖一等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("近5年来，担任并完成国家优秀青年科学基金项目负责人", "科技人才");
        userTypeMap.put("省青年科技奖获得者", "科技人才");
        userTypeMap.put("通过综合考评军队“百千万人才工程”第一层次人才培养人选", "其他人才");
        userTypeMap.put("军队杰出专业技术人才奖获得者", "其他人才");
        userTypeMap.put("军队、国防自然科学奖、技术发明奖、科学技术进步奖（一等奖）获得者（前3位完成人）", "其他人才");
        userTypeMap.put("省“钱江学者”特聘教授", "科技人才");
        userTypeMap.put("通过综合考评的教育部“新世纪优秀人才支持计划”入选者", "教育卫生人才");
        userTypeMap.put("全国模范教师", "教育卫生人才");
        userTypeMap.put("全国优秀教师", "教育卫生人才");
        userTypeMap.put("全国优秀教育工作者", "教育卫生人才");
        userTypeMap.put("全国优秀班主任", "教育卫生人才");
        userTypeMap.put("全国教书育人楷模", "教育卫生人才");
        userTypeMap.put("省功勋教师（省杰出教师）", "教育卫生人才");
        userTypeMap.put("省特级教师", "教育卫生人才");
        userTypeMap.put("省高校教学名师", "教育卫生人才");
        userTypeMap.put("世界500强金融企业中担任中高级以上职务的各类金融专家", "经营管理人才");
        userTypeMap.put("国际学科奥林匹克竞赛金牌获得者指导教师", "教育卫生人才");
        userTypeMap.put("获得国家级荣誉的在杭金融机构主要经营管理人才", "经营管理人才");
        userTypeMap.put("国家级教学成果奖一等奖、二等奖获得者（前3位完成人）", "教育卫生人才");
        userTypeMap.put("近5年担任并完成教育部人文社会科学重点研究基地重大项目或哲学社会科学研究后期资助重大项目负责人", "教育卫生人才");
        userTypeMap.put("国家级金融研究机构主要负责人", "经营管理人才");
        userTypeMap.put("教育部中小学国家课程标准修订核心专家组成员，教育部课程指导委员会学科组委员，教育部国家统编教材核心专家组成员", "教育卫生人才");
        userTypeMap.put("担任国家级重大金融政策规划主要起草人或主持国家级重点金融工程、重大金融项目的研究和建设工作的金融专家", "科技人才");
        userTypeMap.put("青年长江学者", "教育卫生人才");
        userTypeMap.put("近5年来，担任并完成教育部哲学社会科学重大攻关项目第一负责人", "教育卫生人才");
        userTypeMap.put("省级宣传文化系统“五个一批”人才", "文化艺术人才");
        userTypeMap.put("全国精神文明建设“五个一工程”奖单项奖获得者", "文化艺术人才");
        userTypeMap.put("全国文化企业30强提名企业主要经营管理人才（指总公司董事长、总经理）", "经营管理人才");
        userTypeMap.put("文化部“国家动漫政府奖”获得者（指获奖作品的导演、编剧）", "文化艺术人才");
        userTypeMap.put("中国电影“华表奖”个人创作奖获得者", "文化艺术人才");
        userTypeMap.put("中国国际动漫节“金猴奖”综合奖金奖获奖作品的导演、编剧（排名前3位）", "文化艺术人才");
        userTypeMap.put("中国新闻奖一等奖获奖作品的主要作者（排名前3位）", "文化艺术人才");
        userTypeMap.put("中国出版政府奖优秀出版人物奖获得者", "文化艺术人才");
        userTypeMap.put("王选新闻科学技术奖（人才奖）终身成就奖、杰出人才奖", "文化艺术人才");
        userTypeMap.put("全国中青年德艺双馨文艺工作者奖", "文化艺术人才");
        userTypeMap.put("长江韬奋奖获得者", "文化艺术人才");
        userTypeMap.put("中国文化艺术政府奖“文华奖”单项奖（剧作奖、导演奖、编导奖、音乐创作奖、舞台美术奖和表演奖）获得者", "文化艺术人才");
        userTypeMap.put("中国文化艺术政府奖群星奖获得者", "文化艺术人才");
        userTypeMap.put("中国电视飞天奖单项奖获得者及一等奖主要作者（含编剧）、导演和主要演员（排名前3位）", "文化艺术人才");
        userTypeMap.put("中国广播电视节目奖一等奖获得者（主创人员）", "文化艺术人才");
        userTypeMap.put("省级非物质文化遗产传承人", "文化艺术人才");
        userTypeMap.put("全国导游大赛一等奖获得者", "文化艺术人才");
        userTypeMap.put("国家广电总局科技创新奖一等奖（排名前3位完成人）", "文化艺术人才");
        userTypeMap.put("浙江省文化创新团队核心成员（领衔人）", "文化艺术人才");
        userTypeMap.put("文化部优秀专家", "文化艺术人才");
        userTypeMap.put("中国文化艺术政府奖（文华奖、群星奖）获得者", "文化艺术人才");
        userTypeMap.put("中国广播影视大奖（中国电影“华表奖”、中国电视剧“飞天奖”、中国广播电视节目奖）获得者", "文化艺术人才");
        userTypeMap.put("儿童文学“安徒生奖”单项奖获得者", "文化艺术人才");
        userTypeMap.put("科幻文学“雨果奖”单项奖（包括最佳长篇、最佳中长篇、最佳中篇、最佳短篇、最佳编辑奖、最佳美术奖）获得者", "文化艺术人才");
        userTypeMap.put("近5年，在杭州纳税的年度版权收入连续两年超过1000万元，且作品内容健康、突出弘扬社会主义核心价值观的网络作家", "文化艺术人才");
        userTypeMap.put("省哲学社会科学优秀成果奖一等奖获得者（第1位完成人）", "文化艺术人才");
        userTypeMap.put("近5年来，担任并完成国家社科基金重大或重点项目负责人", "科技人才");
        userTypeMap.put("国家卫健委（卫生部）有突出贡献的中青年专家", "教育卫生人才");
        userTypeMap.put("省级名中医", "教育卫生人才");
        userTypeMap.put("省级医学会专业委员会主任", "教育卫生人才");
        userTypeMap.put("通过综合考评的浙江省卫生领军人才培养对象", "教育卫生人才");
        userTypeMap.put("全国老中医药专家学术继承指导老师", "教育卫生人才");
        userTypeMap.put("专科排名全国前10名医院的市级以上重点学科带头人", "教育卫生人才");
        userTypeMap.put("获“全国卫生健康（计生）系统先进工作者”称号且具有正高级卫技专业技术职务的卫技人才", "教育卫生人才");
        userTypeMap.put("省工程勘察设计大师", "教育卫生人才");
        userTypeMap.put("鲁班奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("省级工艺美术大师", "文化艺术人才");
        userTypeMap.put("中国500强企业主要经营管理人才（指总公司的董事长、总经理）", "经营管理人才");
        userTypeMap.put("中国民营企业500强企业主要经营管理人才（指总公司的董事长、总经理）", "经营管理人才");
        userTypeMap.put("世界500强企业任高管的经营管理人才（指总部的副总经理、大洲级区域总裁、首席财务管理人员、首席产品管理人员、首席技术人员等）", "经营管理人才");
        userTypeMap.put("年销售收入30亿元以上集成电路企业高级管理人才和技术研发骨干，且年工资性收入在70万元以上", "经营管理人才");
        userTypeMap.put("年纳税5亿元以上制造业(或国家级高新技术企业)任高管的经营管理人才（指总部的副总经理、大洲级区域总裁、首席账务管理人员、首席产品管理人员、首席技术人员），上年度工资性收入在150万元以上", "经营管理人才");
        userTypeMap.put("具有国家级教练员资格，曾获奥运冠军的教学骨干或奥运冠军的教练员", "其他人才");
        userTypeMap.put("国际标准的召集人", "科技人才");
        userTypeMap.put("中国专利金奖获得者（须为专利发明人，前3位完成人）", "科技人才");
        userTypeMap.put("相当于C类层次的领军人才", "其他人才");
        userTypeMap.put("杭州市杰出人才奖获得者", "科技人才");
        userTypeMap.put("市全球引才“521”计划人选", "科技人才");
        userTypeMap.put("取得正高级专业技术职务任职资格后，获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）", "科技人才");
        userTypeMap.put("(中小学教师选此条)取得正高级专业技术职务任职资格后，获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）", "教育卫生人才");
        userTypeMap.put("曾获奥运冠军的教学骨干", "其他人才");
        userTypeMap.put("奥运冠军的教练员", "其他人才");
        userTypeMap.put("具有国家级教练员资格，曾获世界冠军的教学骨干或世界冠军的教练员", "其他人才");
        userTypeMap.put("杭州市突出贡献引进人才", "其他人才");
        userTypeMap.put("享受杭州市政府特殊津贴人员", "科技人才");
        userTypeMap.put("杭州市钱江特聘专家计划人选", "科技人才");
        userTypeMap.put("通过综合考评的浙江省“151”人才工程第二层次培养人选", "科技人才");
        userTypeMap.put("通过综合考评的杭州市“131”人才工程重点资助和第一层次培养人选", "科技人才");
        userTypeMap.put("从事博士后科研项目结题后，获设区市级以上科技成果（奖励），或取得授权发明专利（前3位完成人），或制定行业标准或国家标准（前3位完成人）", "科技人才");
        userTypeMap.put("杭州市技能大师工作室领衔人", "技能人才");
        userTypeMap.put("省技术能手", "技能人才");
        userTypeMap.put("杭州市首席技师", "技能人才");
        userTypeMap.put("具有特级技师职业资格，并取得以下成果之一的专业技能人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利，制定地方标准、行业标准或国家标准，出版著作，编写教材，在国内外核心期刊发表过学术论文", "教育卫生人才");
        userTypeMap.put("培养产生世界技能大赛获奖选手的教练", "技能人才");
        userTypeMap.put("浙江省“百千万”高技能领军人才（拔尖技能人才）", "技能人才");
        userTypeMap.put("获安全飞行银质奖章飞行员", "技能人才");
        userTypeMap.put("特级飞行员", "技能人才");
        userTypeMap.put("省级技能大师工作室领衔人", "技能人才");
        userTypeMap.put("具有正高级专业技术职务任职资格，并作为主要成员承担过杭州市级以上研究课题或成果获杭州市级以上奖励的专业技术人才", "科技人才");
        userTypeMap.put("杭州市政府“钱江友谊奖”获得者", "其他人才");
        userTypeMap.put("杭州市“115”引智计划高端年薪资助入选专家", "其他人才");
        userTypeMap.put("杭州市科技创新特别贡献奖获得者", "科技人才");
        userTypeMap.put("杭州市成绩突出的科技工作者获得者", "科技人才");
        userTypeMap.put("省（部）自然科学奖、技术发明奖、科学技术进步奖二等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("设区市级（含厅级）科技进步奖（科学技术奖）一等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("杭州市青年科技奖获得者、杭州市十大青年科技英才", "科技人才");
        userTypeMap.put("军队、国防自然科学奖、技术发明奖、科学技术进步奖（二等奖）获得者（前3位完成人）", "其他人才");
        userTypeMap.put("通过综合考评的军队“百千万人才工程”第二层次人才培养人选", "其他人才");
        userTypeMap.put("省优秀班主任", "教育卫生人才");
        userTypeMap.put("省师德楷模", "教育卫生人才");
        userTypeMap.put("省教坛新秀", "教育卫生人才");
        userTypeMap.put("全国五项学科（数学、物理、化学、生物、信息学）竞赛一等奖获得者指导老师", "教育卫生人才");
        userTypeMap.put("全国职业院校技能大赛金牌获得者指导老师", "教育卫生人才");
        userTypeMap.put("省级教学成果一、二等奖获得者（前3位完成人）", "教育卫生人才");
        userTypeMap.put("国家级教学或技能比赛一二等奖获得者", "教育卫生人才");
        userTypeMap.put("全国职业院校教师能力大赛一等奖获得者", "教育卫生人才");
        userTypeMap.put("近5年在四大高校排行榜（泰晤士高等教育排名、QS世界大学排名、USNEWS世界大学排名、软科世界大学学术排名）任一榜单排名前100位大学或近5年在基本科学指标数据库（ESI）排名前1‰学科取得海外博士学位，且在海外连续工作三年以上，并在本领域SCI二区以上期刊发表过学术论文(第一作者或通讯作者)", "科技人才");
        userTypeMap.put("全国中学生竞赛金牌获得者指导老师", "教育卫生人才");
        userTypeMap.put("杭州市宣传文化系统“五个一批”人才", "文化艺术人才");
        userTypeMap.put("王选新闻科学技术奖人才奖特别贡献奖", "文化艺术人才");
        userTypeMap.put("中国新闻奖二等奖获奖作品的主要作者（排名前3位）", "文化艺术人才");
        userTypeMap.put("全国播音主持“金话筒”奖获得者", "文化艺术人才");
        userTypeMap.put("飘萍奖获得者", "文化艺术人才");
        userTypeMap.put("浙江新闻奖一等奖主要作者（排名前3位）", "文化艺术人才");
        userTypeMap.put("入选市级文化创新团队的核心成员（领衔人）", "文化艺术人才");
        userTypeMap.put("省精神文明建设“五个一工程”奖单项奖获得者（排名前3位）", "文化艺术人才");
        userTypeMap.put("入选省重点文化企业、重点文化出口企业、“文化+互联网”创新企业的主要经营管理者（指管理单位第一负责人，或公司董事长、总经理）", "经营管理人才");
        userTypeMap.put("全国导游大赛二等奖、全省导游大赛一等奖获得者", "技能人才");
        userTypeMap.put("中国电视飞天奖二等奖主要作者（含编剧）、导演和主要演员（排名前3位）", "文化艺术人才");
        userTypeMap.put("国家广电总局科技创新奖二等奖（前3位完成人）", "文化艺术人才");
        userTypeMap.put("浙江省广电局科技创新奖一等奖（前3位完成人）", "文化艺术人才");
        userTypeMap.put("中国广播电视节目奖二等奖、中国广播影视大奖提名奖、浙江省广播电视政府一等奖（主创人员）", "文化艺术人才");
        userTypeMap.put("茅盾文学新人奖•网络文学新人奖获得者", "文化艺术人才");
        userTypeMap.put("中国戏剧奖、大众电影百花奖、电影金鸡奖、音乐金钟奖、全国美术展览奖、曲艺牡丹奖、书法兰亭奖、杂技金菊奖、摄影金像奖、民间文艺山花奖、电视金鹰奖、舞蹈荷花奖获得者（各奖项的最高等级奖，须为个人获得）", "文化艺术人才");
        userTypeMap.put("全国优秀儿童文学奖、全国少数民族文学创作骏马奖获得者", "文化艺术人才");
        userTypeMap.put("“啄木鸟杯”中国文艺评论年度优秀作品奖单项奖获奖者", "文化艺术人才");
        userTypeMap.put("曹文轩儿童文学奖单项奖获得者", "文化艺术人才");
        userTypeMap.put("郁达夫小说奖单项奖获得者", "文化艺术人才");
        userTypeMap.put("徐迟报告文学奖单项奖获得者", "文化艺术人才");
        userTypeMap.put("中国书法“兰亭奖”入展者", "文化艺术人才");
        userTypeMap.put("近5年，在杭州纳税的版权收入累计超过1000万元，且作品内容健康、突出弘扬社会主义核心价值观的网络作家", "文化艺术人才");
        userTypeMap.put("省哲学社会科学优秀成果奖二等奖（第1位完成人）", "文化艺术人才");
        userTypeMap.put("通过综合考评的浙江省卫生创新人才培养对象", "教育卫生人才");
        userTypeMap.put("全国省级医学会专业委员会副主任", "教育卫生人才");
        userTypeMap.put("省会城市、计划单列市医学会专业委员会主任", "教育卫生人才");
        userTypeMap.put("杭州市名中医", "教育卫生人才");
        userTypeMap.put("省级高技能人才创新工作室领衔人", "技能人才");
        userTypeMap.put("杭州市大企业大集团（入选杭州工信经济企业百强榜等）主要经营管理人才（指公司的董事长、总经理）", "经营管理人才");
        userTypeMap.put("中国500强企业、中国民营企业500强任高管的经营管理人才（指总部的副总经理、首席财务管理人员、首席产品管理人员、首席技术人员等）", "经营管理人才");
        userTypeMap.put("年销售收入10亿元以上集成电路企业中高级管理人员和技术研发骨干，且年工资性收入在50万元以上", "经营管理人才");
        userTypeMap.put("年纳税5亿元以上制造业(或国家级高新技术企业)中层正职管理和技术人才，上年度工资性收入在100万元以上", "经营管理人才");
        userTypeMap.put("杭州市大企业大集团（上市公司）主要经营管理人才（指公司的董事长、总经理）", "经营管理人才");
        userTypeMap.put("全国金融行业前10强在杭法人主要经营管理人才", "经营管理人才");
        userTypeMap.put("获得省级荣誉的在杭金融机构主要经营管理人才", "经营管理人才");
        userTypeMap.put("入选全国500强企业的金融机构的首席风险控制人员、首席财务管理人员、首席产品管理人员、首席技术人员、首席经济学家", "经营管理人才");
        userTypeMap.put("在浙江省内行业排名前5的金融企业的董事长、总经理", "经营管理人才");
        userTypeMap.put("获得杭州市级以上金融创新奖的团队负责人", "经营管理人才");
        userTypeMap.put("国家标准的第一起草人", "科技人才");
        userTypeMap.put("中国专利银奖、中国外观设计金奖（须为专利发明人或设计人，前3位完成人）", "文化艺术人才");
        userTypeMap.put("省专利奖金奖获得者（须为专利发明人或设计人，前3位完成人）", "科技人才");
        userTypeMap.put("相当于D类层次的领军人才", "其他人才");
        userTypeMap.put("取得副高级专业技术职务任职资格（含高级社会工作师，高级政工师请选择另外一条），获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）", "科技人才");
        userTypeMap.put("（中小学教师选此条）取得副高级专业技术职务任职资格，获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）；", "教育卫生人才");
        userTypeMap.put("取得高级政工师，获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）", "科技人才");
        userTypeMap.put("（中小学教师选此条）具有正高级专业技术职务任职资格的专业技术人才；", "教育卫生人才");
        userTypeMap.put("具有正高级专业技术职务任职资格的专业技术人才", "科技人才");
        userTypeMap.put("通过综合考评的杭州市“131”人才工程第二层次培养人选", "科技人才");
        userTypeMap.put("具有博士学位", "科技人才");
        userTypeMap.put("具有博士研究生学历，获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）；", "科技人才");
        userTypeMap.put("杭州市级技术能手", "技能人才");
        userTypeMap.put("（此条仅限农村实用人才选择）具有高级技师职业资格，并取得以下成果之一的专业技能人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利，制定行业标准或国家标准，出版著作，编写教材，在国内外核心期刊发表过学术论文（仅指农村实用人才）", "技能人才");
        userTypeMap.put("具有高级技师职业资格，并取得以下成果之一的专业技能人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利，制定地方标准、行业标准或国家标准，出版著作，编写教材，在国内外核心期刊发表过学术论文（不含农村实用人才）", "技能人才");
        userTypeMap.put("浙江省“百千万”高技能领军人才（优秀技能人才）", "技能人才");
        userTypeMap.put("杭州市大学生杰出创业人才培育计划入选者", "经营管理人才");
        userTypeMap.put("获安全飞行铜质奖章飞行员", "技能人才");
        userTypeMap.put("一级飞行员", "其他人才");
        userTypeMap.put("在国内外核心期刊发表过重要学术论文的博士研究生或博士学位获得者", "科技人才");
        userTypeMap.put("杭州市级技能大师工作室领衔人", "技能人才");
        userTypeMap.put("具有副高级专业技术职务任职资格，并作为主要成员承担过杭州市级以上研究课题或成果获杭州市级以上奖励的专业技术人才", "科技人才");
        userTypeMap.put("通过综合考评的军队“百千万人才工程”第三层次人才培养人选", "其他人才");
        userTypeMap.put("省春蚕奖获得者", "教育卫生人才");
        userTypeMap.put("浙江省级教学或技能比赛一二等奖获得者", "教育卫生人才");
        userTypeMap.put("杭州市教坛新秀", "教育卫生人才");
        userTypeMap.put("省教坛新秀", "教育卫生人才");
        userTypeMap.put("通过综合考评的杭州青年设计师发现计划（建筑设计）重点培养人选", "文化艺术人才");
        userTypeMap.put("通过综合考评的杭州市青年文艺家发现计划重点培养人选", "文化艺术人才");
        userTypeMap.put("通过综合考评的省影视艺术青年人才“新光计划”培养人选", "文化艺术人才");
        userTypeMap.put("通过综合考评的杭州市文创人才培养工程重点培养人选", "文化艺术人才");
        userTypeMap.put("国家广播电视总局推优动画系列片、“原动力”计划扶持漫画、游戏作品的获得者（指获奖作品的导演、编剧）", "文化艺术人才");
        userTypeMap.put("杭州新闻奖一等奖主要作者（排名前3位）", "文化艺术人才");
        userTypeMap.put("市级精神文明建设“五个一工程”奖单项奖（排名前3位）", "文化艺术人才");
        userTypeMap.put("特级导游员", "技能人才");
        userTypeMap.put("金牌导游员", "技能人才");
        userTypeMap.put("金牌讲解员", "技能人才");
        userTypeMap.put("高级导游员", "技能人才");
        userTypeMap.put("杭州市非物质文化遗产传承人", "文化艺术人才");
        userTypeMap.put("浙江省旅游行业技术能手", "技能人才");
        userTypeMap.put("全国导游大赛三等奖、全省导游大赛二等奖获得者", "技能人才");
        userTypeMap.put("通过综合考评省青年文艺“新松”计划培养人选", "文化艺术人才");
        userTypeMap.put("杭州广播电视政府奖一等奖（主创人员）", "文化艺术人才");
        userTypeMap.put("浙江美术奖、书法奖、摄影金像奖、戏剧金桂奖、电影凤凰奖、电视牡丹奖、音乐奖、舞蹈奖、曲艺奖、杂技奖、民间文艺映山红奖、文艺评论奖单项奖获得者", "文化艺术人才");
        userTypeMap.put("通过综合考评省青年文艺“新峰”“新荷”计划培养人选", "文化艺术人才");
        userTypeMap.put("网络文学双年奖金奖获得者", "文化艺术人才");
        userTypeMap.put("浙江省青年文学之星", "文化艺术人才");
        userTypeMap.put("杭州市社科优秀青年人才称号获得者", "文化艺术人才");
        userTypeMap.put("近5年来，担任并完成国家社科基金一般（含青年、后期资助等）项目负责人", "文化艺术人才");
        userTypeMap.put("省哲学社会科学优秀成果三等奖（第1位完成人）", "文化艺术人才");
        userTypeMap.put("省医坛新秀", "教育卫生人才");
        userTypeMap.put("通过综合考评的杭州青年设计师发现计划（工业设计）重点培养人选", "文化艺术人才");
        userTypeMap.put("通过综合考评的中国杰出女装设计师发现计划重点培养人选", "文化艺术人才");
        userTypeMap.put("杭州市工艺美术大师", "文化艺术人才");
        userTypeMap.put("杭州市规模以上企业的主要经营管理人才（指公司的董事长、总经理）", "经营管理人才");
        userTypeMap.put("入围中国软件业务收入前百家、中国互联网企业百强、中国软件和信息技术服务综合竞争力百强企业的首席技术官、首席信息官、首席运营官", "经营管理人才");
        userTypeMap.put("杭州市规模以上或主营业务收入超过1000万元信息企业的核心技术人才（指公司技术总监、总工程师）", "经营管理人才");
        userTypeMap.put("年销售收入1亿元以上集成电路企业年工资性收入达30万元的管理人员和技术研发骨干", "经营管理人才");
        userTypeMap.put("年纳税5亿元以上制造业(或国家级高新技术企业)人才，上年度工资性收入在50万元以上", "经营管理人才");
        userTypeMap.put("杭州市工业设计精英人物", "科技人才");
        userTypeMap.put("曾获世界冠军的教学骨干", "其他人才");
        userTypeMap.put("世界冠军的教练员", "技能人才");
        userTypeMap.put("具有高级教练员以上资格，曾获亚运会、全运会冠军的教学骨干或亚运会、全运会冠军教练员", "技能人才");
        userTypeMap.put("在浙江省内排名前5的或获得市级荣誉的金融机构总部的总经理及首席风险控制人员、首席财务管理人员、首席产品管理人员、首席技术人员、首席经济学家", "经营管理人才");
        userTypeMap.put("获得市级荣誉或持有注册金融分析师、精算师、证券保荐人等证书的在杭金融企业的主要负责人（指公司董事长、总经理）", "经营管理人才");
        userTypeMap.put("通过综合考评的杭州青年设计师发现计划（广告设计）重点培养人选", "技能人才");
        userTypeMap.put("行业标准、省级地方标准的第一起草人", "科技人才");
        userTypeMap.put("中国专利优秀奖、中国外观设计银奖获得者（须为专利设计人，前3位完成人）", "科技人才");
        userTypeMap.put("省专利奖优秀奖获得者（须为专利发明人或设计人，前3位完成人）", "科技人才");
        userTypeMap.put("相当于E类层次的高级人才", "其他人才");
        userTypeMap.put("在国内外核心期刊发表过重要学术论文的博士", "科技人才");
        userTypeMap.put("具有高级技师职业资格证书，并作为主要成员承担过杭州市级以上研究课题或成果获杭州市级以上奖励的技能人才（不含农村实用人才）", "技能人才");
        userTypeMap.put("省“千人计划”人选", "其他人才");
        userTypeMap.put("国家“千人计划”人选", "其他人才");
        userTypeMap.put("省自然科学奖、技术发明奖、科学技术进步奖二等奖获得者前3名", "科技人才");
        userTypeMap.put("具有副高级专业技术职务任职资格，并作为主要成员承担过杭州市级以上研究课题或成果获杭州市级以上奖励的专业技术人才", "科技人才");
        userTypeMap.put("省自然科学奖、技术发明奖、科学技术进步奖一等奖获得者前3名", "科技人才");
        userTypeMap.put("杭州市科技进步奖一等奖获得者前3名", "科技人才");
        userTypeMap.put("市全球引才“521”计划人选", "科技人才");
        userTypeMap.put("在国内外核心期刊发表过重要学术论文的博士研究生或博士学位获得者", "科技人才");
        userTypeMap.put("杭州市杰出人才奖获得者", "科技人才");
        userTypeMap.put("具有博士研究生学历或博士学位，获得以下专业技术成果之一的人才：获设区市级以上奖励，取得授权专利（前3位完成人），制定行业标准或国家标准（前3位完成人），在国内外核心期刊发表过学术论文（前2位作者）", "科技人才");
        userTypeMap.put("取得正高级专业技术职务任职资格后，获得以下专业技术成果之一的人才：获设区市级以上奖励，取得授权专利（前3位完成人），制定地方、行业标准或国家标准（前3位完成人）", "科技人才");
        userTypeMap.put("取得副高级专业技术职务任职资格后，获得以下专业技术成果之一的人才：获设区市级以上奖励，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人）", "科技人才");
        userTypeMap.put("设区市级科技进步奖一等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("省自然科学奖、技术发明奖、科学技术进步奖二等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("具有高级技师职业资格，并取得以下成果之一的专业技能人才：作为主要成员承担过设区市级以上研究课题，获设区市级以上奖励，取得授权专利，制定地方标准、行业标准或国家标准（不含农村实用人才）", "技能人才");
        userTypeMap.put("省自然科学奖、技术发明奖、科学技术进步奖一等奖获得者（前3位完成人）", "科技人才");
        userTypeMap.put("全国宣传文化系统“四个一批”人才", "文化艺术人才");
        userTypeMap.put("具有高级政工师资格，并作为主要成员承担过杭州市级以上研究课题或成果获杭州市级以上奖励的人才", "科技人才");
        userTypeMap.put("中国戏剧奖、大众电影百花奖、电影金鸡奖、音乐金钟奖、全国美术展览奖、曲艺牡丹奖、书法兰亭奖、杂技金菊奖、摄影金像奖、民间文艺山花奖、电视金鹰奖、舞蹈荷花奖获得者", "文化艺术人才");
        userTypeMap.put("年纳税5亿元以上制造业企业人才，上年度工资性收入在50万元以上", "经营管理人才");
        userTypeMap.put("取得副高级专业技术职务任职资格（含高级社会工作师），获得以下专业技术成果之一的人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利（前3位完成人），制定地方标准、行业标准或国家标准（前3位完成人），出版著作，编写教材，在国内外核心期刊发表过学术论文（前2位作者）", "科技人才");
        userTypeMap.put("年纳税5亿元以上制造业或国家级高新技术企业人才，上年度工资性收入在50万元以上", "经营管理人才");
        userTypeMap.put("年纳税5亿制造业或年纳税5亿国家级高新技术企业人才，上年度工资性收入在50万元以上", "经营管理人才");
        userTypeMap.put("年纳税5亿元以上制造业或国家级高新技术企业任高管的经营管理人才（指总部的副总经理、大洲级区域总裁、首席账务管理人员、首席产品管理人员、首席技术人员），上年度工资性收入在150万元以上", "经营管理人才");
        userTypeMap.put("年纳税5亿元以上制造业或国家级高新技术企业中层正职管理和技术人才，上年度工资性收入在100万元以上", "经营管理人才");
        userTypeMap.put("年纳税5亿制造业或年纳税5亿国家级高新技术企业中层正职管理和技术人才，上年度工资性收入在100万元以上", "经营管理人才");
        userTypeMap.put("杭州市全球引才“521”计划入选人才", "科技人才");
        userTypeMap.put("具有高级技师职业资格，并取得以下成果之一的专业技能人才：获区、县（市）级以上奖励，承担设区市级以上课题、科研项目，取得授权专利，制定行业标准或国家标准，出版著作，编写教材，在国内外核心期刊发表过学术论文（仅指农村实用人才）", "技能人才");
        userTypeMap.put("年纳税5亿元以上制造业或国家级高新技术企业主要经营管理人才（指公司董事长、总经理），上年度工资性收入在200万元以上。", "经营管理人才");
    }

    /**
     * 根据用户认定项目, 获取是哪种类型
     *
     * @param applyOption
     * @return
     */
    public static String getUserTypeByApplyOption(String applyOption) {
        if (StringUtils.isBlank(applyOption)) {
            return null;
        }
        return userTypeMap.get(applyOption.trim());
    }

}