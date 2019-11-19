package com.mlzj.commontest.demo.datastruct;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 二叉树
 *
 * @author yhl
 * @date 2019/10/30
 */
@SuppressWarnings("all")
public class MLzjTree<T> {

    /**
     * 左高
     */
    private static final Integer LH = 1;
    /**
     * 右搞
     */
    private static final Integer RH = -1;
    /**
     * 等高
     */
    private static final Integer EH = 0;

    private Set<Integer> balanceSet = Sets.newHashSet(EH, LH, RH);
    /**
     * 具体数据
     */
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
        return compareAndSet(null, root, data).getCode();
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
    private TreeNode compareAndSet(TreeNode parentNode, TreeNode currentNode, T data) {
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
            return treeNode;
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
     * 通过数据删除元素
     *
     * @param data 数据
     */
    public void removeByData(T data) {
        int hash = this.hash(data);
        this.removeByCode(hash);
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

    /**
     * 新增数据并构建平衡二叉树
     *
     * @param data
     */
    public Integer addForAvl(T data) {
        if (Objects.isNull(root)) {
            return initRootForAvl(data);
        }
        return addTreeNodeAve(null, root, data).getCode();
    }

    /**
     * 向平衡二叉树中新增节点
     * @param parentNode 父节点
     * @param current 当前节点
     * @param data 数据
     * @return 新增的节点
     */
    private TreeNode addTreeNodeAve(TreeNode parentNode, TreeNode current, T data) {
        Integer code = this.hash(data);
        if (current == null) {
            TreeNode treeNode = new TreeNode();
            treeNode.setParent(parentNode);
            treeNode.setCode(code);
            treeNode.setHeight(0);
            treeNode.setIndex(dataArray.size());
            dataArray.add(data);
            if (code > parentNode.code) {
                parentNode.setRightChild(treeNode);
            } else {
                parentNode.setLeftChild(treeNode);
            }
            return treeNode;
        }
        if (code > current.code) {
            TreeNode insertTreeNode = addTreeNodeAve(current, current.rightChild, data);
            current.setHeight(this.getHeight(current));
            this.changeTree(current);
            return insertTreeNode;
        } else {
            TreeNode insertTreeNode = addTreeNodeAve(current, current.leftChild, data);
            current.setHeight(this.getHeight(current));
            this.changeTree(current);
            return insertTreeNode;
        }
    }


    /**
     * 改变树的结构
     *
     * @param current 当前的节点
     * @param insert  插入的节点
     */
    private void changeTree(TreeNode current) {
        if (this.getBf(current) == 0) {
            return;
        }
        //左树过高
        if (this.getBf(current) > LH) {
            if (this.getBf(current.getLeftChild()) > 0 ) {
                this.rightRotate(current, LH);
            } else {
                this.leftRotate(current.getLeftChild(), LH);
                this.rightRotate(current, LH);
            }
        }
        //右树过高
        if (this.getBf(current) < RH) {
            if (this.getBf(current.getRightChild()) < 0) {
                this.leftRotate(current, RH);
            } else {
                this.rightRotate(current.getRightChild(), RH);
                this.leftRotate(current, RH);
            }

        }
    }

    /**
     * 计算当前节点的平衡因子
     * @param current 当前的节点
     * @return 平衡因子 即左树和右树高度差
     */
    private Integer getBf(TreeNode current) {
        Integer leftHeight = current.getLeftChild() == null ? -1 : current.getLeftChild().getHeight();
        Integer rightHeight = current.getRightChild() == null ? -1 : current.getRightChild().getHeight();
        return leftHeight - rightHeight;
    }

    /**
     * 计算层高
     *
     * @param treeNode 节点
     * @return 返回层高
     */
    private Integer getHeight(TreeNode treeNode) {
        if (Objects.isNull(treeNode.getRightChild()) && Objects.isNull(treeNode.getLeftChild())) {
            return 0;
        }

        if (Objects.isNull(treeNode.getRightChild())) {
            return treeNode.getLeftChild().getHeight() + 1;
        }
        if (Objects.isNull(treeNode.getLeftChild())) {
            return treeNode.getRightChild().getHeight() + 1;
        }
        return Math.max(treeNode.getLeftChild().getHeight(), treeNode.getRightChild().getHeight()) + 1;
    }

    /**
     * 初始化平衡二叉树
     * @param data 插入的数据
     * @return 新加入节点的code
     */
    private Integer initRootForAvl(T data) {
        TreeNode treeNode = new TreeNode();
        treeNode.setIndex(0);
        treeNode.setHeight(EH);
        int code = this.hash(data);
        treeNode.setCode(code);
        root = treeNode;
        dataArray.add(data);
        return code;
    }

    /**
     * 左旋 托儿
     *
     * @param treeNode 进行左旋的节点
     */
    private void leftRotate(TreeNode treeNode, Integer highCode) {
        TreeNode tempTreeNode = treeNode.getRightChild().getLeftChild();
        treeNode.getRightChild().setLeftChild(treeNode);
        if (Objects.isNull(treeNode.getParent())) {
            root = treeNode.getRightChild();
            root.setParent(null);
        } else {
            if (Objects.equals(highCode, RH)) {
                treeNode.getParent().setRightChild(treeNode.getRightChild());
            } else {
                treeNode.getParent().setLeftChild(treeNode.getRightChild());
            }
            treeNode.getRightChild().setParent(treeNode.getParent());
        }
        if (Objects.nonNull(tempTreeNode)) {
            tempTreeNode.setParent(treeNode);
        }
        treeNode.setParent(treeNode.getRightChild());
        treeNode.setRightChild(tempTreeNode);
        treeNode.setHeight(this.getHeight(treeNode));
        treeNode.getParent().setHeight(this.getHeight(treeNode.getParent()));
    }

    /**
     * 右旋 托儿
     *
     * @param treeNode 进行右旋的节点
     */
    private void rightRotate(TreeNode treeNode, Integer highCode) {
        TreeNode tempTreeNode = treeNode.getLeftChild().getRightChild();
        treeNode.getLeftChild().setRightChild(treeNode);
        if (Objects.isNull(treeNode.getParent())) {
            root = treeNode.getLeftChild();
            root.setParent(null);
        } else {
            if (Objects.equals(highCode, LH)) {
                treeNode.getParent().setLeftChild(treeNode.getLeftChild());
            } else {
                treeNode.getParent().setRightChild(treeNode.getLeftChild());
            }
            treeNode.getLeftChild().setParent(treeNode.getParent());
        }
        if (Objects.nonNull(tempTreeNode)) {
            tempTreeNode.setParent(treeNode);
        }
        treeNode.setParent(treeNode.getLeftChild());
        treeNode.setLeftChild(tempTreeNode);
        treeNode.setHeight(this.getHeight(treeNode));
        treeNode.getParent().setHeight(this.getHeight(treeNode.getParent()));
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
         * 平衡因子
         */
        private Integer height;

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
