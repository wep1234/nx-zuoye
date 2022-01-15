//package com.nx.day05.anno.processor;
//
//import com.google.auto.service.AutoService;
//import com.nx.anno.WepData;
//import com.sun.source.tree.Tree;
//import com.sun.tools.javac.api.JavacTrees;
//import com.sun.tools.javac.code.Flags;
//import com.sun.tools.javac.code.Type;
//import com.sun.tools.javac.processing.JavacProcessingEnvironment;
//import com.sun.tools.javac.tree.JCTree;
//import com.sun.tools.javac.tree.TreeMaker;
//import com.sun.tools.javac.tree.TreeTranslator;
//import com.sun.tools.javac.util.*;
//
//import javax.annotation.processing.*;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import javax.tools.Diagnostic;
//import java.util.Set;
//
///**
// * 注解处理器，编译时生成getter和setter方法
// *
// */
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
//@SupportedAnnotationTypes("com.nx.day05.anno.WepData")
//@AutoService(Processor.class)
//public class WepProcessor extends AbstractProcessor {
//
//    //编译时期输入日志
//    private Messager messager;
//    //待处理的抽象语法树
//    private JavacTrees javacTrees;
//    //封装了创建AST节点的一些方法
//    private TreeMaker treeMaker;
//    //提供了创建标识符的方法
//    private Names names;
//
//    //初始化相应的操作
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnv) {
//        super.init(processingEnv);
//        this.messager = processingEnv.getMessager();
//        this.javacTrees = JavacTrees.instance(processingEnv);
//        Context context = ((JavacProcessingEnvironment)processingEnv).getContext();
//        this.treeMaker = TreeMaker.instance(context);
//        this.names = Names.instance(context);
//    }
//
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        //获取注解标注的元素集合
//        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(WepData.class);
//        elementsAnnotatedWith.forEach(e->{
//            JCTree tree = javacTrees.getTree(e);
//            //获取当前元素的语法树
//            tree.accept(new TreeTranslator(){
//                //访问类节点
//                @Override
//                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
//                    List<JCTree.JCVariableDecl> jcVariableDeclList = List.nil();
//                    //在抽象树中找出所有的变量
//                    for(JCTree jcTree : jcClassDecl.defs){
//                        if(jcTree.getKind().equals(Tree.Kind.VARIABLE)){
//                            //获取变量语法树，添加到待处理列表
//                            JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) jcTree;
//                            jcVariableDeclList = jcVariableDeclList.append(jcVariableDecl);
//                        }
//                    }
//                    //对类变量生成相关的getter/setter方法
//                    if(jcVariableDeclList.size() > 0){
//                        jcVariableDeclList.forEach(jcVariableDecl -> {
//                            treeMaker.pos = jcVariableDecl.pos;
//                            messager.printMessage(Diagnostic.Kind.NOTE,jcVariableDecl.getName()+"正在生成getter/setter方法");
//                            jcClassDecl.defs = jcClassDecl.defs.prepend(makeGetterMethodDecl(jcVariableDecl));
//                            jcClassDecl.defs = jcClassDecl.defs.prepend(makeSetterMethodDecl(jcVariableDecl));
//                        });
//                    }
//
//                    super.visitClassDef(jcClassDecl);
//                }
//            });
//        });
//        return true;
//    }
//
//    /**
//     * 生成getter方法
//     * @param jcVariableDecl
//     * @return
//     */
//    private JCTree makeGetterMethodDecl(JCTree.JCVariableDecl jcVariableDecl) {
//        JCTree.JCModifiers jcModifiers =  treeMaker.Modifiers(Flags.PUBLIC);
//        JCTree.JCExpression retrunType = jcVariableDecl.vartype;//方法返回类型
//        Name name =  getNewMethodName("get",jcVariableDecl.getName());
//        //生成表达式 retrun this.xxx
//        JCTree.JCStatement athis =
//                treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.name));
//        List<JCTree.JCStatement> jcStatementList = List.nil();
//        jcStatementList = jcStatementList.append(athis);
//        //构建代码块
//        JCTree.JCBlock block = treeMaker.Block(0,jcStatementList);
//        //泛型参数列表
//        List<JCTree.JCTypeParameter> methodGenericParams = List.nil();
//        //参数列表
//        List<JCTree.JCVariableDecl> parameters = List.nil();
//        //异常抛出列表
//        List<JCTree.JCExpression> throwsClauses = List.nil();
//        //非自定义注解类中的方法，defaultValue为null
//        JCTree.JCExpression defaultValue = null;
//        return treeMaker.MethodDef(jcModifiers, name, retrunType,methodGenericParams,parameters,throwsClauses,block,defaultValue);
//    }
//
//    /**
//     * 生成setter方法
//     * @param jcVariableDecl
//     * @return
//     */
//    private JCTree makeSetterMethodDecl(JCTree.JCVariableDecl jcVariableDecl) {
//        JCTree.JCModifiers jcModifiers =  treeMaker.Modifiers(Flags.PUBLIC);
//        Name name =  getNewMethodName("set",jcVariableDecl.getName());
//        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
//        //生成表达式
//        //this.xx = xxx 赋值语句
//        JCTree.JCExpressionStatement aThis = makeAssignment(treeMaker.Select(treeMaker.Ident(names.fromString("this")),
//                jcVariableDecl.getName()) ,treeMaker.Ident(jcVariableDecl.getName()));
//        statements.append(aThis);
//        //构建代码块
//        JCTree.JCBlock block = treeMaker.Block(0,statements.toList());
//        List<JCTree.JCTypeParameter> methodGenericParams = List.nil();
//        //生成入参，
//        JCTree.JCVariableDecl paramter =
//                treeMaker.VarDef(treeMaker.Modifiers(Flags.PARAMETER),jcVariableDecl.getName(),jcVariableDecl.vartype,null);
//        List<JCTree.JCVariableDecl> paramters = List.of(paramter);
//        //返回语句构建
//        JCTree.JCExpression methodType = treeMaker.Type(new Type.JCVoidType());
//        //异常抛出列表
//        List<JCTree.JCExpression> throwsClauses = List.nil();
//        //非自定义注解类中的方法，defaultValue为null
//        JCTree.JCExpression defaultValue = null;
//
//        //构造方法
//        return treeMaker.MethodDef(jcModifiers,name,methodType,methodGenericParams,paramters,throwsClauses,block,defaultValue);
//    }
//
//    private Name getNewMethodName(String prefix, Name name) {
//        String s = name.toString();
//        return names.fromString(prefix+s.substring(0,1).toUpperCase()+s.substring(1));
//    }
//
//    /**
//     * 生成赋值语句 x=1这种
//     * @param lhs 左边
//     * @param rhs 右边
//     * @return
//     */
//    private JCTree.JCExpressionStatement makeAssignment(JCTree.JCExpression lhs, JCTree.JCExpression rhs) {
//        return treeMaker.Exec(
//                treeMaker.Assign(
//                        lhs,
//                        rhs
//                )
//        );
//    }
//
//
//
//
//}
