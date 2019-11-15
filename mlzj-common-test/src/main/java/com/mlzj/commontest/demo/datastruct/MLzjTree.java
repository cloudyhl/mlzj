package com.mlzj.commontest.demo.datastruct;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 二叉树
 *
 * @author yhl
 * @date 2019/10/30
 */
@SuppressWarnings("all")
public class MLzjTree<T> {

    List<T> dataArray = new ArrayList<>();

    private TreeNode root;

    int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 添加元素
     *
     * @param data 数据
     * @return code
     */
    public Integer add(T data) {
        if (root == null) {
            return initRoot(data);
        }
        return compareAndSet(null, root, data);
    }


    /**
     * 根据code返回code对应的节点
     *
     * @param current 当前节点
     * @param code    code
     * @return code对应的节点
     */
    public TreeNode getTreeNodeByCode(TreeNode current, Integer code) {
        if (Objects.isNull(current)) {
            return null;
        }
        if (Objects.equals(current.code, code)) {
            return current;
        }
        if (current.code > code) {
            return getTreeNodeByCode(current.leftChild, code);
        } else {
            return getTreeNodeByCode(current.rightChild, code);
        }
    }

    /**
     * 比较并set子节点
     *
     * @param parentNode  父节点
     * @param currentNode 当前节点
     * @param data        数据
     */
    private Integer compareAndSet(TreeNode parentNode, TreeNode currentNode, T data) {
        int code = this.hash(data);
        if (currentNode == null) {
            TreeNode treeNode = new TreeNode();
            treeNode.setParent(parentNode);
            treeNode.setCode(code);
            treeNode.setIndex(dataArray.size());
            dataArray.add(data);
            if (code > parentNode.code) {
                parentNode.setRightChild(treeNode);
            } else {
                parentNode.setLeftChild(treeNode);
            }
            return code;
        }
        if (code > currentNode.code) {
            return compareAndSet(currentNode, currentNode.rightChild, data);
        } else {
            return compareAndSet(currentNode, currentNode.leftChild, data);
        }
    }


    /**
     * 通过code回去当前code的元素
     *
     * @param code code码
     * @return code的元素
     */
    public T getByCode(Integer code) {
        return this.getByCode(root, code);
    }

    /**
     * 通过code查询 先序遍历 中序遍历 后序遍历  只是改变判断过程的顺序
     *
     * @param current 当前元素节点
     * @param code    查询的code
     * @return 返回查询的结果
     */
    private T getByCode(TreeNode current, Integer code) {
        if (this.getTreeNodeByCode(current, code) != null) {
            return this.dataArray.get(this.getTreeNodeByCode(current, code).getIndex());
        }
        return null;
    }

    /**
     * 通过code删除节点
     *
     * @param code code
     */
    public void removeByCode(Integer code) {
        this.removeByCode(root, code);
    }


    /**
     * 通过code 从current处开始查找删除节点
     *
     * @param current 当前节点
     * @param code    需要删除的code
     */
    private synchronized void removeByCode(TreeNode current, Integer code) {
        if (Objects.equals(current.code, code)) {
            CheckRemove checkRemove = this.checkRemove(current);
            this.remove(current, checkRemove);
            if (!checkRemove.isHasTwo()) {
                this.reBuildIndex(root, current.getIndex());
                this.dataArray.remove(current.getIndex().intValue());
            }
            return;
        }
        if (current.code > code) {
            removeByCode(current.leftChild, code);
        } else {
            removeByCode(current.rightChild, code);
        }
        return;
    }

    /**
     * 重新设置索引
     *
     * @param current      当前节点
     * @param currentIndex 被删除节点的索引
     */
    private void reBuildIndex(TreeNode current, Integer currentIndex) {
        if (Objects.isNull(current)) {
            return;
        }
        if (current.getIndex() > currentIndex) {
            current.setIndex(current.getIndex() - 1);
        }
        if (Objects.nonNull(current.getLeftChild())) {
            reBuildIndex(current.getLeftChild(), currentIndex);
        }
        if (Objects.nonNull(current.getRightChild())) {
            reBuildIndex(current.getRightChild(), currentIndex);
        }
    }

    /**
     * 删除节点
     *
     * @param current     当前节点
     * @param checkRemove 校验删除
     */
    private void remove(TreeNode current, CheckRemove checkRemove) {
        if (checkRemove.isRoot() && checkRemove.hasNone) {
            root = null;
            return;
        }
        if (checkRemove.hasNone) {
            if (current.getCode() > current.getParent().getCode()) {
                current.getParent().setRightChild(null);
            }
            if (current.getCode() < current.getParent().getCode()) {
                current.getParent().setLeftChild(null);
            }
            return;
        }
        if (checkRemove.hasOne) {
            if (current.getCode() > current.getParent().getCode()) {
                current.getParent().setRightChild(current.getLeftChild() == null ? current.getRightChild() : null);
            }
            if (current.getCode() < current.getParent().getCode()) {
                current.getParent().setLeftChild(current.getLeftChild() == null ? current.getRightChild() : null);
            }
            return;
        }
        if (checkRemove.hasTwo) {
            TreeNode min = this.getMin(current.getRightChild());
            Integer minCode = min.getCode();
            Integer minIndex = min.getIndex();
            dataArray.set(current.getIndex(), dataArray.get(min.getIndex()));
            this.removeByCode(current, min.getCode());
            current.setCode(minCode);

        }
        return;
    }

    /**
     * 查找当前节点最小的节点
     *
     * @param current 当前节点
     * @return 最小的节点
     */
    private TreeNode getMin(TreeNode current) {
        if (Objects.nonNull(current.getLeftChild())) {
            this.getMin(current.getLeftChild());
        }
        return current;
    }

    /**
     * 检查当前节点所处的情况
     *
     * @param treeNode 当前节点
     */
    private CheckRemove checkRemove(TreeNode treeNode) {
        CheckRemove checkRemove = new CheckRemove();
        if (treeNode.equals(root) && treeNode.getIndex() == 0) {
            checkRemove.setRoot(true);
        }
        if (Objects.isNull(treeNode.getLeftChild()) && Objects.isNull(treeNode.getRightChild())) {
            checkRemove.setHasNone(true);
        }
        if ((Objects.isNull(treeNode.getLeftChild()) && Objects.nonNull(treeNode.getRightChild())) || (Objects.nonNull(treeNode.getLeftChild()) && Objects.isNull(treeNode.getRightChild()))) {
            checkRemove.setHasOne(true);

        }
        if (Objects.nonNull(treeNode.getLeftChild()) && Objects.nonNull(treeNode.getRightChild())) {
            checkRemove.setHasTwo(true);
        }
        return checkRemove;
    }

    /**
     * 初始化根元素
     *
     * @param data
     */
    private Integer initRoot(T data) {
        TreeNode treeNode = new TreeNode();
        treeNode.setIndex(0);
        int code = this.hash(data);
        treeNode.setCode(code);
        root = treeNode;
        dataArray.add(data);
        return code;
    }

    @Data
    private class TreeNode {
        /**
         * 父级节点
         */
        private TreeNode parent;

        /**
         * 唯一的code
         */
        private Integer code;

        /**
         * 当前节点
         */
        private Integer index;

        /**
         * 左儿子
         */
        private TreeNode leftChild;

        /**
         * 右儿子
         */
        private TreeNode rightChild;
    }

    @Data
    private class CheckRemove {

        /**
         * 是否根节点删除
         */
        private boolean root = false;

        /**
         * 只有一个节点
         */
        private boolean hasOne = false;

        /**
         * 有两个子节点
         */
        private boolean hasTwo = false;

        /**
         * 是否没有子节点
         */
        private boolean hasNone = false;
    }
}
