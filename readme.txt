1��ͨ��git clone https://github.com/lianzhengkun/easyshopping.git ������Ŀ¼��
2���뱣֤��Ļ�������maven���������úá�cd ���ղ���Ŀ��Ŀ¼��
3��ִ�����mvn eclipse:eclipse -Dwtpversion=2.0 ������������eclipse��Ŀ������web��Ŀ�����Ա�eclipse��web��Ŀ��ǡ���һ��ִ��ʱ�������Ҫ�Ƚϳ��������ĵȴ���
4��ͨ��eclipse����Ŀ���룬���ʱ����Բ鿴��Ŀȫò��
5���޸�src/main/resources�µ������ļ���
   ��һ���ļ���hibernate.cfg.xml������ļ��������������ɱ�ṹ��������Լ���������������޸ģ����ݿ������û���������
   �ڶ����ļ���resources.properties������ļ���ҪΪSpring��ʶ���������ã���Ϊ��Ŀ������Spring������sessionFactory�Ĺ�����������ļ���Spring����ģ�����Ҳ��Ҫ��Ҹ������������������޸ġ����յ�һ���ļ���
6��ִ��src/main/javaԴ�ļ����µ�com.ibm.util.TableManager�࣬�������ȡhibernate.cfg.xml������ļ��������ͨ��JPAע���ʵ����,����TableManager��������Щע�����ɱ�ṹ��
7����Ϊ��Ŀ��ʹ�õ���һ��������jar��������û�ж�Ӧ��pom��������Ҫ�����ͨ��eclipse�������������jar���ŵ���Ŀ��Build Path�У����jar������webapp/WEB-INF/lib�¡�
8�����ɱ�ṹ�󣬾Ϳ��԰���Ŀ����eclipse��ͨ�����Լ���web���������в����ˣ���ȻҲ����ͨ������mvn clean install jetty:run ��������Ŀ������jetty������������ͨ����������з����ˡ�enjoy it!

