package ru.podstavkov.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import ru.podstavkov.annotation.UnproducableBullet;

@Component
public class UnproducableBulletBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// Получаем имена всех BeanDefinition для доступа к каждому из них
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		// Перебираем все имена
		for (String name : beanDefinitionNames) {
			// Получаем BeanDefinition по имени
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			/*
			 * Получаем имя класса создаваемого бина, чтобы проверить, содержит ли он
			 * аннотацию UnsupportedCameraRoll
			 */
			String className = beanDefinition.getBeanClassName();
			try {
				// Получаем класс по имени
				Class<?> beanClass = Class.forName(className);
				// *Пытаемся получить объект аннотации и ее значение,

				UnproducableBullet annotation = beanClass.getAnnotation(UnproducableBullet.class);
				if (annotation != null) {
					Class usingCameraRollName = annotation.usingBulletClass();
					beanDefinition.setBeanClassName(usingCameraRollName.getName());
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
