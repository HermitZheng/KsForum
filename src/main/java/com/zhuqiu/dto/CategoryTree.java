package com.zhuqiu.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/25
 */

@Data
public class CategoryTree implements Serializable {

    private static final long serialVersionUID = 4843146284812472476L;
    private String parentName;

    private Integer parentId;

    private Integer allCount;

    private List<Child> childList;

    public class Child {

        private String childName;

        private Integer childId;

        private Integer articleCount;

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }

        public Integer getChildId() {
            return childId;
        }

        public void setChildId(Integer childId) {
            this.childId = childId;
        }

        public Integer getArticleCount() {
            return articleCount;
        }

        public void setArticleCount(Integer articleCount) {
            this.articleCount = articleCount;
        }

        public Child(String childName, Integer childId, Integer articleCount) {
            this.childName = childName;
            this.childId = childId;
            this.articleCount = articleCount;
        }

        public Child() {
        }
    }
}
