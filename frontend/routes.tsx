import HelloWorldView from 'Frontend/views/helloworld/HelloWorldView.js';
import LoginView from 'Frontend/views/login/LoginView.js';
import MainLayout from 'Frontend/views/MainLayout.js';
import { lazy } from 'react';
import { createBrowserRouter, RouteObject } from 'react-router-dom';
import { protectRoutes } from '@hilla/react-auth';

const AboutView = lazy(async () => import('Frontend/views/about/AboutView.js'));
const AccessDeniedView = lazy(async () => import('Frontend/views/error/AccessDeniedView.js'));
const ICView = lazy(async () => import('Frontend/views/ic/ICView.js'));
const ManagerView = lazy(async () => import('Frontend/views/manager/ManagerView.js'));

export const routes: RouteObject[] = protectRoutes([
  {
    element: <MainLayout />,
    handle: { title: 'Main' },
    children: [
      { path: '/', element: <HelloWorldView />, handle: { title: 'Hello World', requiresLogin: true } },
      { path: '/about', element: <AboutView />, handle: { title: 'About', requiresLogin: true } },
      { path: '/ic', element: <ICView />, handle: { title: 'IC', rolesAllowed: ['IC'] } },
      { path: '/manager', element: <ManagerView />, handle: { title: 'Manager', rolesAllowed: ['Manager'] } },
      { path: '/access-denied', element: <AccessDeniedView />, handle: { title: 'Access denied' } },
    ],
  },
  { path: '/login', element: <LoginView /> },
], "/access-denied");

export default createBrowserRouter(routes);