import React from 'react';
import TodoItem from "./TodoItem";
import styled from 'styled-components'
import {TodoObject} from "../types/Todo";

type LineProps = {
  screenSize: 'MOBILE' | 'TABLET' | 'DESKTOP',
  items: TodoObject[];
}

const Container = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`

const SubContainer = styled.div`
  width: 95%;
  margin: 0 !important;
`

export default function Line(props: LineProps) {
  function getClassName(): string {
    switch (props.screenSize) {
      case 'TABLET':
        return 'column is-one-third'
      case 'DESKTOP':
        return 'column is-one-fifth'
      case 'MOBILE':
        return 'column is-half'
      default:
        return 'column'
    }
  }

  return (
    <Container>
    <SubContainer className="columns">
      {
        props.items.map((item: TodoObject): React.ReactElement => {
          return <div key={item.id} className={getClassName()}>
            <TodoItem item={item}/>
          </div>
        })
      }
    </SubContainer>
    </Container>
  )


}
