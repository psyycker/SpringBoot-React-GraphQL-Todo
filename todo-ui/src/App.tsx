import React, { useState, useLayoutEffect, useEffect } from 'react';
import { useQuery } from '@apollo/react-hooks';
import { gql } from 'apollo-boost';
import './App.css';
import LoadingComponent from "./components/LoadingComponent";
import ErrorComponent from "./components/ErrorComponent";
import {TodoObject} from "./types/Todo";
import Line from "./components/Line";
import useWindowSize from "./hooks/useWindowSizeHook";

const getTodosQuery = gql`
    query todos {
        todos {
            id
            name
            description
            done
            category {
                name
                id
            }
        }
    }
`

const TABLET = 1400;
const MOBILE = 1000;

function generateKey(acc: string, item: TodoObject): string {
  acc += item.id;
  return acc;
}

function App() {
  const { loading, error, data } = useQuery(getTodosQuery);
  const [screenSize, setScreenSize] = useState<'MOBILE' | 'TABLET' | 'DESKTOP'>();

  useEffect(() => {
    if (screenSize == null) {
      refreshScreenSize(window.innerWidth);
    }
  }, [screenSize])

  function refreshScreenSize(width): void {
    if (width <= MOBILE && screenSize !== 'MOBILE') {
      setScreenSize('MOBILE');
    } else if (width <= TABLET && screenSize !== 'TABLET') {
      setScreenSize('TABLET');
    } else if (width > TABLET && screenSize !== 'DESKTOP') {
      setScreenSize('DESKTOP');
    }
  }

  useWindowSize((event) => {
    refreshScreenSize(event.target.innerWidth);
  })


  if (loading) {
    return <LoadingComponent />
  }

  if (error) {
    return <ErrorComponent />
  }

  function renderContent(): React.ReactElement[] {
    const numberPerLine = screenSize === 'DESKTOP' ? 5 : screenSize === 'TABLET' ? 3 : 2;
    const lines: React.ReactElement[] = [];
    let line: TodoObject[] = [];
    data.todos.forEach((item: TodoObject): void => {
      line.push(item);
      if (line.length === numberPerLine) {
        lines.push(<Line items={line} screenSize={screenSize} key={line.reduce(generateKey, '')}/>);
        line = [];
      }
    })
    if (line.length > 0) {
      lines.push(<Line items={line} screenSize={screenSize} key={line.reduce(generateKey, '')}/>);
    }
  return lines;
  }

  return (
    <div>
      {renderContent()}
    </div>
  );
}

export default App;
