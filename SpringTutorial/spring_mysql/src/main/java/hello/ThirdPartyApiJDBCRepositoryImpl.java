package hello;

import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ThirdPartyApiJDBCRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ThirdPartyApiRepository myRepository;

    public ThirdPartyApi save(ThirdPartyApi o) {
        return myRepository.save(o);
    }

    public void delete(Integer id_val) {
        myRepository.delete(id_val);
    }

    public ThirdPartyApi findOne(Integer id_val) {
        return myRepository.findOne(id_val);
    }

    // TODO: Batch job
    public List<ThirdPartyApi> saveMany(List<ThirdPartyApi> s) {
        List<ThirdPartyApi> result = new ArrayList<>();

        for (ThirdPartyApi a : s) {
            result.add(myRepository.save(a));
        }
        return result;
    }

    public String find_with_paging(String[] params, String orderClause, int page_num, int PAGE_SIZE, List<ThirdPartyApi> result) {

        // page_num: zero index
        StringBuilder sbWhere = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        sb.append("select id,name,url,token,refresh_token,username,password,start_date,end_date,description,base_id,cron,auto_upload from thirdparty_api");

        if (params != null && params.length > 0) {
            if (params.length % 3 != 0) {
                // throw new SQLException("Wrong parameter; must divided by 3");
                return "Wrong parameter; must divided by 3";
            }

            String swhere = "";
            int n_clause = 0;

            for (int i = 0; i < params.length / 3; i++) {
                String a1 = params[i * 3];
                String a2 = params[i * 3 + 1];
                String a3 = params[i * 3 + 2];

                if (a3.equals("<") || a3.equals(">") || a3.equals("=") || a3.equals("like")) {
                    if (n_clause++ == 0) {
                        // swhere = "( " + a1 + " " + a3 + " " + a2 +" )";
                        sbWhere.append("( " + a1 + " " + a3 + " " + a2 + " )");
                    } else {
                        // swhere += " and ( " + a1 + " " + a3 + " " + a2 + "
                        // )";
                        sbWhere.append(" and ( " + a1 + " " + a3 + " " + a2 + " )");
                    }
                }
            }

            if (n_clause > 0) {
                sb.append(" where ");
                sb.append(sbWhere.toString());
            }
        }

        if (orderClause != null) {
            sb.append(" order by ");
            sb.append(orderClause);
        }

        if (page_num >=0 && PAGE_SIZE >= 0) {
            int startRecord = page_num * PAGE_SIZE;
            sb.append(" LIMIT ");
            sb.append(startRecord);
            sb.append(",");
            sb.append(PAGE_SIZE);
        }


        List<Map<String,Object>> rows = null;
        result.clear();

        rows = jdbcTemplate.queryForList(sb.toString());
        for (Map row : rows) {

            ThirdPartyApi o = new ThirdPartyApi();

            
            o.setId((Integer) row.get("id") );
            
            o.setName((String) row.get("name") );
            
            o.setUrl((String) row.get("url") );
            
            o.setToken((String) row.get("token") );
            
            o.setRefresh_token((String) row.get("refresh_token") );
            
            o.setUsername((String) row.get("username") );
            
            o.setPassword((String) row.get("password") );
            
            o.setStart_date(() row.get("start_date") );
            
            o.setEnd_date(() row.get("end_date") );
            
            o.setDescription((String) row.get("description") );
            
            o.setBase_id((Integer) row.get("base_id") );
            
            o.setCron((String) row.get("cron") );
            
            o.setAuto_upload((Boolean) row.get("auto_upload") );
            

            result.add(o);
        }

        return null;
    }


    public String fullCRUD_Test() {

        /*------------------------------------------------------------
            Full CRUD Test
            1/ Create 1 object
                - Update one field
                - Delete it
            2/ Create bulk Object
                - Search them 
                - Delete them
         *------------------------------------------------------------*/
        int ret_test = 0;
        int ret = 0;
        StringBuilder sbRet = new StringBuilder();

        boolean b_checkUpdate = true;
        boolean b_cleaning = true;
        int N_BULK = 2050;
        List<ThirdPartyApi> lsNew = new ArrayList<ThirdPartyApi>();
        ThirdPartyApi o_saved = null;
        ThirdPartyApi o = new ThirdPartyApi();
        ThirdPartyApi o_update = null;

        o.setId((int)Math.random()*10);
        o.setName(new Date().toString().substring(0, 10));
        o.setUrl(new Date().toString().substring(0, 10));
        o.setToken(new Date().toString().substring(0, 10));
        o.setRefresh_token(new Date().toString().substring(0, 10));
        o.setUsername(new Date().toString().substring(0, 10));
        o.setPassword(new Date().toString().substring(0, 10));
        o.setStart_date();
        o.setEnd_date();
        o.setDescription(new Date().toString().substring(0, 10));
        o.setBase_id((int)Math.random()*10);
        o.setCron(new Date().toString().substring(0, 10));
        o.setAuto_upload(new Date().getTime() % 2 == 0? true : false);
        

        sbRet.append("Full CRUD Test for ThirdPartyApi\r\n");


        System.out.println("\tCreating object...");
        o_saved = save(o);

        if (o_saved == null) {
            sbRet.append("\t\tFailed to create\r\n");
            return sbRet.toString();
        } else {
            sbRet.append("\t\t[PASSED]\r\n"); 
        }

        sbRet.append("\tUpdating object...\r\n");

        String new_val_name = new Date().toString().substring(0, 10);
        String new_val_url = new Date().toString().substring(0, 10);
        String new_val_token = new Date().toString().substring(0, 10);
        String new_val_refresh_token = new Date().toString().substring(0, 10);
        String new_val_username = new Date().toString().substring(0, 10);
        String new_val_password = new Date().toString().substring(0, 10);
        new_val_start_date = ;
        new_val_end_date = ;
        String new_val_description = new Date().toString().substring(0, 10);
        Integer new_val_base_id = (int)Math.random()*10;
        String new_val_cron = new Date().toString().substring(0, 10);
        Boolean new_val_auto_upload = new Date().getTime() % 2 == 0? true : false;
        
        o_saved.setName(new_val_name);
        o_saved.setUrl(new_val_url);
        o_saved.setToken(new_val_token);
        o_saved.setRefresh_token(new_val_refresh_token);
        o_saved.setUsername(new_val_username);
        o_saved.setPassword(new_val_password);
        o_saved.setStart_date(new_val_start_date);
        o_saved.setEnd_date(new_val_end_date);
        o_saved.setDescription(new_val_description);
        o_saved.setBase_id(new_val_base_id);
        o_saved.setCron(new_val_cron);
        o_saved.setAuto_upload(new_val_auto_upload);
        

        ThirdPartyApi oRet = save(o_saved);

        if (oRet == null) {
            sbRet.append("\t\tFailed to Update\r\n");            
        } else {
            sbRet.append("\t\t[PASSED]");
        }

        sbRet.append("\tChecking Updating result ...");
        o_update = findOne(o_saved.getId());

        if (o_update != null) {
            b_checkUpdate &= _compare_val(o.getName(), o_update.getName())==0;
            b_checkUpdate &= _compare_val(o.getUrl(), o_update.getUrl())==0;
            b_checkUpdate &= _compare_val(o.getToken(), o_update.getToken())==0;
            b_checkUpdate &= _compare_val(o.getRefresh_token(), o_update.getRefresh_token())==0;
            b_checkUpdate &= _compare_val(o.getUsername(), o_update.getUsername())==0;
            b_checkUpdate &= _compare_val(o.getPassword(), o_update.getPassword())==0;
            b_checkUpdate &= _compare_val(o.getStart_date(), o_update.getStart_date())==0;
            b_checkUpdate &= _compare_val(o.getEnd_date(), o_update.getEnd_date())==0;
            b_checkUpdate &= _compare_val(o.getDescription(), o_update.getDescription())==0;
            b_checkUpdate &= _compare_val(o.getBase_id(), o_update.getBase_id())==0;
            b_checkUpdate &= _compare_val(o.getCron(), o_update.getCron())==0;
            b_checkUpdate &= _compare_val(o.getAuto_upload(), o_update.getAuto_upload())==0;
            
        } else {
            b_checkUpdate = false;
        }

        if (!b_checkUpdate) {
            sbRet.append("\t\tUpdating not correct\r\n");            
        } else {
            sbRet.append("\t\t[PASSED]\r\n");
        }

        sbRet.append("\tCleaning object...\r\n");
        delete(o_saved.getId());

        ThirdPartyApi o_find = findOne(o_saved.getId());
        if (o_find != null) {
            sbRet.append("\t\tFailed to Delete \r\n");
        }  else {
            sbRet.append("\t\t[PASSED]\r\n");
        }

        sbRet.append("\tCreating bulk " + N_BULK + " object...\r\n");
        for (int i=0;i<N_BULK;i++) {
            ThirdPartyApi o2 = new ThirdPartyApi();
            o2.setName(new Date().toString().substring(0, 10));o2.setUrl(new Date().toString().substring(0, 10));o2.setToken(new Date().toString().substring(0, 10));o2.setRefresh_token(new Date().toString().substring(0, 10));o2.setUsername(new Date().toString().substring(0, 10));o2.setPassword(new Date().toString().substring(0, 10));o2.setStart_date();o2.setEnd_date();o2.setDescription(new Date().toString().substring(0, 10));o2.setBase_id((int)Math.random()*10);o2.setCron(new Date().toString().substring(0, 10));o2.setAuto_upload(new Date().getTime() % 2 == 0? true : false);

            lsNew.add(o2);
        }

        List<ThirdPartyApi> lsSaved = null;
        lsSaved = save(lsNew);

        if (lsSaved == null) {
            sbRet.append("\t\tFailed to create bulk\r\n");
        } else {
            sbRet.append("\t\t[PASSED]\r\n");
        }
        sbRet.append("\tSearching bulk data ...\r\n");

        boolean bFindBulk = true;
        for (ThirdPartyApi o_each : lsSaved) {
            o_find = findOne(o_each.getId());

            if (o_find == null) {
                bFindBulk = false;
            }
        }

        if (!bFindBulk) {
            sbRet.append("\t\tFailed search bulk\r\n");
        } else {
            sbRet.append("\t\t[PASSED]\r\n");
        }

        sbRet.append("\tCleaning bulk data ...\r\n");
        for (ThirdPartyApi o_each : lsSaved) {
            delete(o_each.getId());

            o_find = findOne(o_each.getId());
                        
            if (o_find != null) {
                b_cleaning = false;
            }
        }

        if (!b_cleaning) {
            sbRet.append("\t\tFailed cleaning bulk\r\n");               
        }

        sbRet.append("\t\t[FINISHED]\r\n");
        return sbRet.toString();
    }

    protected int _compare_val(String s1, String s2) {
        if (s1==null && s2 == null) {
            return 0;
        } else if (s1 != null) {
            return s1.compareTo(s2);
        } else {
            return s2.compareTo(s1);
        }
    }
    
    protected int _compare_val(Integer s1, Integer s2) {
        int i1 = s1 == null ? -Integer.MAX_VALUE : s1.intValue();
        int i2 = s2 == null ? -Integer.MAX_VALUE : s2.intValue();
        
        return i1 == i2 ? 0 : (i1< i2 ? -1 : 1);        
    }
    
    protected int _compare_val(Long s1, Long s2) {
        long  i1 = s1 == null ? -Long.MAX_VALUE : s1.longValue();
        long i2 = s2 == null ? -Long.MAX_VALUE : s2.longValue();
        
        return i1 == i2 ? 0 : (i1< i2 ? -1 : 1);        
    }
    
    protected int _compare_val(Double s1, Double  s2) {
        double i1 = s1 == null ? -Double.MAX_VALUE : s1.doubleValue();
        double i2 = s2 == null ? -Double.MAX_VALUE : s2.doubleValue();
        
        return i1 == i2 ? 0 : (i1< i2 ? -1 : 1);        
    }

    protected int _compare_val(Float s1, Float  s2) {
        float i1 = s1 == null ? -Float.MAX_VALUE : s1.floatValue();
        float i2 = s2 == null ? -Float.MAX_VALUE : s2.floatValue();

        return i1 == i2 ? 0 : (i1< i2 ? -1 : 1);
    }

    protected int _compare_val(Short  s1, Short  s2) {
        short i1 = s1 == null ? -Short.MAX_VALUE : s1.shortValue();
        short i2 = s2 == null ? -Short.MAX_VALUE : s2.shortValue();
        
        return i1 == i2 ? 0 : (i1< i2 ? -1 : 1);        
    }

    protected int _compare_val(Timestamp  s1, Timestamp s2) {
        long i1 = s1 == null ? -0 : s1.getTime();
        long i2 = s2 == null ? -0 : s2.getTime();
        
        return i1 == i2 ? 0 : (i1< i2 ? -1 : 1);        
    }

    protected int _compare_val(Boolean s1, Boolean s2) {
        boolean i1 = s1 == null ? false : s1.booleanValue();
        boolean i2 = s2 == null ? false : s2.booleanValue();

        return i1 == i2 ? 0 : 1;
    }

    protected int _compare_val(BigDecimal s1, BigDecimal s2) {
        BigDecimal i1 = s1 == null ? new BigDecimal(0) : s1;
        BigDecimal i2 = s2 == null ? new BigDecimal(0): s2;

        return i1.compareTo(i2);
    }    

}